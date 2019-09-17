package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.dto.MyRedPacketDTO;
import com.ch.entity.*;
import com.ch.enums.RedPacketEnum;
import com.ch.model.UserUseRedPacketParam;
import com.ch.service.SysRedPacketService;
import com.ch.service.ViewRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewRedPacketServiceImpl implements ViewRedPacketService {

    @Autowired
    RedPacketMapper redPacketMapper;

    @Autowired
    SysRedPacketService sysRedPacketService;

    @Autowired
    UserRedPacketMapper userRedPacketMapper;

    @Autowired
    GoodsTypeMapper goodsTypeMapper;

    @Autowired
    GoodsSkuMapper goodsSkuMapper;

    @Autowired
    SpikeGoodsMapper spikeGoodsMapper;

    @Autowired
    GoodsMapper goodsMapper;


    @Override
    public ResponseResult judgeRedPacket(Integer userId) {
        ResponseResult result = new ResponseResult();
        RedPacketExample redPacketExample = new RedPacketExample();
        redPacketExample.createCriteria().andStatusEqualTo(1);
        boolean flg = false;
        List<RedPacket> redPackets = redPacketMapper.selectByExample(redPacketExample);
        for (RedPacket redPacket : redPackets) {
            if (isEffectiveDate(new Date(), redPacket.getSendBeginDate(), redPacket.getSendEndDate())) {
                flg = true;
            }
        }
        result.setData(flg);
        return result;
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author jqlin
     */
    private static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ResponseResult redPacketList(Integer userId) {
        ResponseResult result = new ResponseResult();
        RedPacketExample redPacketExample = new RedPacketExample();
        redPacketExample.createCriteria().andStatusEqualTo(1);
        List<RedPacket> redPackets = redPacketMapper.selectByExample(redPacketExample);
        for (RedPacket redPacket : redPackets) {
            boolean b = sysRedPacketService.updateRedPacketStatus(redPacket.getId());
            if (!b) {
                redPacket.setStatus(0);
            }
        }
        List<RedPacket> redPacketList = redPackets.stream()
                .filter(redPacket -> redPacket.getStatus() == 1 && RedPacketEnum.MEMBER.name().equals(redPacket.getRedPacketType()))
                .collect(Collectors.toList());
        result.setData(redPacketList);
        return result;
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public ResponseResult receiveRedPacket(Integer userId, Integer redPacketId) {
        ResponseResult result = new ResponseResult();
        RedPacket redPacket = redPacketMapper.selectByPrimaryKey(redPacketId);
        boolean effectiveDate = isEffectiveDate(new Date(), redPacket.getSendBeginDate(), redPacket.getSendEndDate());
        if (effectiveDate) {
            if (RedPacketEnum.MEMBER.name().equals(redPacket.getRedPacketType())) {
                if (redPacket.getStock() > 0) {
                    UserRedPacketExample userRedPacketExample = new UserRedPacketExample();
                    userRedPacketExample.createCriteria().andRedPacketIdEqualTo(redPacketId).andUserIdEqualTo(userId);
                    long count = userRedPacketMapper.countByExample(userRedPacketExample);
                    if (count == 0) {
                        int isChange = redPacketMapper.decreaseRedPacket(redPacket.getId(), redPacket.getVersion());
                        if (isChange != 0) {
                            UserRedPacket userRedPacket = new UserRedPacket();
                            userRedPacket.setUserId(userId);
                            userRedPacket.setStatus(0);
                            userRedPacket.setCreateDate(new Date());
                            userRedPacket.setFaceValue(redPacket.getFaceValue());
                            userRedPacket.setReceiveDate(new Date());
                            userRedPacket.setRedPacketId(redPacket.getId());
                            userRedPacketMapper.insert(userRedPacket);
                        }
                    }
                }
            }
        } else {
            redPacket.setStatus(0);
            redPacketMapper.updateByPrimaryKey(redPacket);
            result.setCode(600);
            result.setError("该红包不在有效期内");
            result.setError_description("该红包不在有效期内");
            return result;
        }
        return result;
    }


    @Override
    public ResponseResult myRedPacketList(Integer userId) {
        ResponseResult result = new ResponseResult();
        UserRedPacketExample userRedPacketExample = new UserRedPacketExample();
        userRedPacketExample.createCriteria().andUserIdEqualTo(userId);
        List<UserRedPacket> userRedPackets = userRedPacketMapper.selectByExample(userRedPacketExample);
        List<MyRedPacketDTO> myRedPacketDTOList = new ArrayList<>();
        for (UserRedPacket userRedPacket:userRedPackets) {
            MyRedPacketDTO myRedPacketDTO = new MyRedPacketDTO();
            RedPacket redPacket = redPacketMapper.selectByPrimaryKey(userRedPacket.getRedPacketId());
            if (BeanUtils.isNotEmpty(redPacket)) {
                myRedPacketDTO.setId(userRedPacket.getRedPacketId());
                myRedPacketDTO.setName(redPacket.getName());
                myRedPacketDTO.setFaceValue(redPacket.getFaceValue());
                myRedPacketDTO.setSendEndDate(redPacket.getSendEndDate().getTime());
                myRedPacketDTO.setSendBeginDate(redPacket.getSendBeginDate().getTime());
                myRedPacketDTO.setSuperposition(redPacket.getSuperposition());
                myRedPacketDTO.setMinPrice(redPacket.getMinPrice());
                myRedPacketDTO.setUseBeginDate(redPacket.getUseBeginDate().getTime());
                myRedPacketDTO.setUseEndDate(redPacket.getUseEndDate().getTime());
                myRedPacketDTO.setRedPacketType(redPacket.getRedPacketType());
                myRedPacketDTO.setStatus(userRedPacket.getStatus());
                myRedPacketDTO.setDescribe(redPacket.getDescribe());
                if (redPacket.getGoodsRange() == 0) {
                    myRedPacketDTO.setGoodsRange("全品类");
                } else {
                    GoodsType goodsType = goodsTypeMapper.selectByPrimaryKey(redPacket.getGoodsRange());
                    if (BeanUtils.isNotEmpty(goodsType)) {
                        myRedPacketDTO.setGoodsRange(goodsType.getTitle());
                    }
                }
                myRedPacketDTOList.add(myRedPacketDTO);
            }
        }
        result.setData(myRedPacketDTOList);
        return result;
    }

    @Override
    @Transactional
    public void sendRedPacket(Integer userId, String redPacketType) {
        RedPacketExample redPacketExample = new RedPacketExample();
        redPacketExample.createCriteria().andRedPacketTypeEqualTo(redPacketType).andStatusEqualTo(1);
        List<RedPacket> redPacketList = redPacketMapper.selectByExample(redPacketExample);
        for (RedPacket redPacket:redPacketList) {
            boolean b = sysRedPacketService.updateRedPacketStatus(redPacket.getId());
            if (b) {
                UserRedPacketExample userRedPacketExample = new UserRedPacketExample();
                userRedPacketExample.createCriteria().andRedPacketIdEqualTo(redPacket.getId()).andUserIdEqualTo(userId);
                long count = userRedPacketMapper.countByExample(userRedPacketExample);
                if (count == 0) {
                    int isChange = redPacketMapper.decreaseRedPacket(redPacket.getId(), redPacket.getVersion());
                    if (isChange != 0) {
                        UserRedPacket userRedPacket = new UserRedPacket();
                        userRedPacket.setUserId(userId);
                        userRedPacket.setStatus(0);
                        userRedPacket.setCreateDate(new Date());
                        userRedPacket.setFaceValue(redPacket.getFaceValue());
                        userRedPacket.setReceiveDate(new Date());
                        userRedPacket.setRedPacketId(redPacket.getId());
                        userRedPacketMapper.insert(userRedPacket);
                    }
                }
            }
        }
    }

    @Override
    public ResponseResult userUseRedPacket(UserUseRedPacketParam packetParam, Integer userId) {
        ResponseResult result = new ResponseResult();
        Long price = 0L;
        UserRedPacketExample userRedPacketExample = new UserRedPacketExample();
        userRedPacketExample.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(0).andRedPacketIdEqualTo(packetParam.getRedPacketId());
        List<UserRedPacket> userRedPackets = userRedPacketMapper.selectByExample(userRedPacketExample);
        if (userRedPackets.stream().findFirst().isPresent()) {
            UserRedPacket userRedPacket = userRedPackets.stream().findFirst().get();
            RedPacket redPacket = redPacketMapper.selectByPrimaryKey(userRedPacket.getRedPacketId());
            if (isEffectiveDate(new Date(), redPacket.getUseBeginDate(), redPacket.getUseEndDate())) {
                if (packetParam.getOrderPrice() >= redPacket.getMinPrice()) {
                    if (redPacket.getSuperposition() == 0) {
                        for (Integer skuId:packetParam.getSkuIds()) {
                            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(skuId);
                            SpikeGoodsExample spikeGoodsExample = new SpikeGoodsExample();
                            spikeGoodsExample.createCriteria().andSkuIdEqualTo(skuId).andGoodsIdEqualTo(goodsSku.getGoodsId());
                            List<SpikeGoods> spikeGoodsList = spikeGoodsMapper.selectByExample(spikeGoodsExample);
                            if (spikeGoodsList.stream().findFirst().isPresent()) {
                                SpikeGoods spikeGoods = spikeGoodsList.stream().findFirst().get();
                                if (isEffectiveDate(new Date(), spikeGoods.getBeginDate(), spikeGoods.getEndDate())) {
                                    result.setCode(600);
                                    result.setError_description("该红包不可与其他活动同时参与，请选择其他红包");
                                } else {
                                    price = redPacket.getFaceValue();
                                }
                            }
                        }
                    } else {
                        if (redPacket.getGoodsRange() == 0) {
                            price = redPacket.getFaceValue();
                        } else {
                            Long accordPrice = 0L;
                            List<Integer> goodsTypeIdList = new ArrayList<>();
                            goodsTypeIdList.add(redPacket.getGoodsRange());
                            GoodsType goodsType = goodsTypeMapper.selectByPrimaryKey(redPacket.getGoodsRange());
                            if (BeanUtils.isNotEmpty(goodsType)) {
                                if (goodsType.getParentId() == 0) {
                                    GoodsTypeExample goodsTypeExample = new GoodsTypeExample();
                                    goodsTypeExample.createCriteria().andParentIdEqualTo(goodsType.getId());
                                    List<GoodsType> goodsTypes = goodsTypeMapper.selectByExample(goodsTypeExample);
                                    for (GoodsType type:goodsTypes) {
                                        goodsTypeIdList.add(type.getId());
                                    }
                                }
                            }
                            for (Integer skuId:packetParam.getSkuIds()) {
                                GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(skuId);
                                Goods goods = goodsMapper.selectByPrimaryKey(goodsSku.getGoodsId());
                                for (Integer goodsTypeId:goodsTypeIdList) {
                                    if (goods.getCatrgoryId().equals(goodsTypeId)) {
                                        accordPrice += goodsSku.getPresentPrice();
                                    }
                                }
                            }
                            if (accordPrice >= redPacket.getMinPrice()) {
                                price = redPacket.getFaceValue();
                            } else {
                                result.setCode(600);
                                result.setError_description("该品类商品未达到红包使用要求，请选择其他红包");
                            }
                        }
                    }
                } else {
                    result.setCode(600);
                    result.setError_description("订单总额未达到红包要求，请选择其他红包");
                }
            } else {
                result.setCode(600);
                result.setError_description("该红包不在使用时间范围内，请选择其他红包");
            }
        } else {
            result.setCode(600);
            result.setError_description("该红包已被使用，请选择其他红包");
        }
        result.setData(price);
        return result;
    }
}