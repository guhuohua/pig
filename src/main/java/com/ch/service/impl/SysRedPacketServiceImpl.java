package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.RedPacketMapper;
import com.ch.dao.UserRedPacketMapper;
import com.ch.dto.GrantRedPacketDTO;
import com.ch.dto.RedPacketDTO;
import com.ch.dto.RedPacketListDTO;
import com.ch.dto.RedPacketSet;
import com.ch.entity.RedPacket;
import com.ch.entity.RedPacketExample;
import com.ch.entity.UserRedPacketExample;
import com.ch.enums.RedPacketEnum;
import com.ch.model.GrantListParam;
import com.ch.model.RedPacketParam;
import com.ch.service.SysRedPacketService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SysRedPacketServiceImpl implements SysRedPacketService {

    @Autowired
    RedPacketMapper redPacketMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRedPacketMapper userRedPacketMapper;

    @Override
    @Transactional
    public ResponseResult save(RedPacketDTO redPacketDTO) {
        ResponseResult result = new ResponseResult();
        for (RedPacketSet redPacketSet:redPacketDTO.getRedPacketSets()) {
            RedPacket redPacket = new RedPacket();
            redPacket.setName(redPacketDTO.getName());
            redPacket.setSendBeginDate(new Date(redPacketDTO.getSendBeginDate()));
            redPacket.setSendEndDate(new Date(redPacketDTO.getSendEndDate()));
            redPacket.setStock(redPacketDTO.getStock());
            BigDecimal faceValue = redPacketDTO.getFaceValue().multiply(new BigDecimal("100.00"));
            redPacket.setFaceValue(faceValue.longValue());
            redPacket.setGoodsRange(redPacketDTO.getGoodsRange());
            redPacket.setRedPacketType(redPacketDTO.getRedPacketType());
            redPacket.setSuperposition(redPacketSet.getSuperposition());
            BigDecimal minPrice = redPacketSet.getMinPrice().multiply(new BigDecimal("100.00"));
            redPacket.setMinPrice(minPrice.longValue());
            redPacket.setUseBeginDate(new Date(redPacketSet.getUseBeginDate()));
            redPacket.setUseEndDate(new Date(redPacketSet.getUseEndDate()));
            redPacket.setDescribe(redPacket.getDescribe());
            redPacket.setStatus(1);
            redPacket.setVersion(0L);
            redPacketMapper.insert(redPacket);
        }
        return result;
    }

    @Override
    public ResponseResult list(RedPacketParam redPacketParam) {
        ResponseResult result = new ResponseResult();
        PageHelper.startPage(redPacketParam.getCurrentPage(), redPacketParam.getPageSize());
        List<RedPacketListDTO> list = redPacketMapper.list(redPacketParam.getName(), redPacketParam.getUseBeginDate(), redPacketParam.getUseEndDate(), redPacketParam.getStatus(), redPacketParam.getRedPacketType());
        PageInfo<RedPacketListDTO> pageInfo = new PageInfo<>(list);
        for (RedPacketListDTO redPacketListDTO:pageInfo.getList()) {
            UserRedPacketExample userRedPacketExample = new UserRedPacketExample();
            userRedPacketExample.createCriteria().andRedPacketIdEqualTo(redPacketListDTO.getId());
            long receiveCount = userRedPacketMapper.countByExample(userRedPacketExample);
            redPacketListDTO.setReceiveCount(receiveCount);
            UserRedPacketExample userRedPacketExample2 = new UserRedPacketExample();
            userRedPacketExample2.createCriteria().andRedPacketIdEqualTo(redPacketListDTO.getId()).andStatusEqualTo(1);
            long usedCount = userRedPacketMapper.countByExample(userRedPacketExample);
            redPacketListDTO.setUsedCount(usedCount);
            boolean b = updateRedPacketStatus(redPacketListDTO.getId());
            if (!b) {
                redPacketListDTO.setStatus(0);
            }
        }
        result.setData(pageInfo);
        return result;
    }

    @Override
    public ResponseResult info(Integer id) {
        ResponseResult result = new ResponseResult();
        RedPacketDTO redPacketDTO = new RedPacketDTO();
        RedPacket redPacket = redPacketMapper.selectByPrimaryKey(id);
        redPacketDTO.setId(redPacket.getId());
        redPacketDTO.setName(redPacket.getName());
        redPacketDTO.setSendBeginDate(redPacket.getSendBeginDate().getTime());
        redPacketDTO.setSendEndDate(redPacket.getSendEndDate().getTime());
        redPacketDTO.setStock(redPacket.getStock());
        BigDecimal bigDecimal = new BigDecimal(redPacket.getFaceValue());
        redPacketDTO.setFaceValue(bigDecimal);
        redPacketDTO.setGoodsRange(redPacket.getGoodsRange());
        redPacketDTO.setRedPacketType(redPacket.getRedPacketType());
        List<RedPacketSet> redPacketSets = new ArrayList<>();
        if (RedPacketEnum.REGISTER.name().equals(redPacket.getRedPacketType())) {
            RedPacketExample redPacketExample = new RedPacketExample();
            redPacketExample.createCriteria().andRedPacketTypeEqualTo(RedPacketEnum.REGISTER.name());
            List<RedPacket> redPackets = redPacketMapper.selectByExample(redPacketExample);
            for (RedPacket packet:redPackets) {
                RedPacketSet redPacketSet = new RedPacketSet();
                redPacketSet.setSuperposition(packet.getSuperposition());
                redPacketSet.setMinPrice(new BigDecimal(packet.getMinPrice()));
                redPacketSet.setUseBeginDate(packet.getUseBeginDate().getTime());
                redPacketSet.setUseEndDate(packet.getUseEndDate().getTime());
                redPacketSet.setDescribe(packet.getDescribe());
                redPacketSets.add(redPacketSet);
            }
        }
        redPacketDTO.setRedPacketSets(redPacketSets);
        result.setData(redPacketDTO);
        return result;
    }

    @Override
    public ResponseResult grantList(GrantListParam param) {
        ResponseResult result = new ResponseResult();
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        List<GrantRedPacketDTO> grantRedPacketDTOS = redPacketMapper.grantList(param.getName(), param.getStatus());
        PageInfo<GrantRedPacketDTO> pageInfo = new PageInfo<>(grantRedPacketDTOS);
        result.setData(pageInfo);
        return result;
    }

    @Override
    public boolean updateRedPacketStatus(int id) {
        RedPacket redPacket = redPacketMapper.selectByPrimaryKey(id);
        boolean effectiveDate = isEffectiveDate(new Date(), redPacket.getSendBeginDate(), redPacket.getSendEndDate());
        if (!effectiveDate) {
            redPacket.setStatus(0);
            redPacketMapper.updateByPrimaryKey(redPacket);
        }
        return effectiveDate;
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
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
}
