/**
 * Author: 常富文
 * Date:   2019/4/16 17:24
 * Description: 订单
 */

package com.ch.service.impl;

import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.dto.OrderDto;
import com.ch.entity.*;
import com.ch.service.SolrService;
import com.ch.service.ViewOrderService;
import com.ch.util.RandomUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

@Service
public class ViewOrderServiceImpl implements ViewOrderService {

    @Autowired
    GoodsOrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    GoodsSkuAttributeMapper goodsSkuAttributeMapper;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    SpecificationAttributeMapper specificationAttributeMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserAddressMapper userAddressMapper;
    @Autowired
    SolrService solrService;
    @Autowired
    SpikeGoodsMapper spikeGoodsMapper;

    @Override
    public ResponseResult addOrder(OrderDto[] orderDtoList, String openId, Integer shopId) {
        ResponseResult result = new ResponseResult();
        Long totalFee = 0l;
        Long orderFee = 0l;
        GoodsOrder order = new GoodsOrder();
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        if (userInfos.size() > 0) {
            userInfo = userInfos.get(0);
        }
        UserAddress userAddress = null;
        UserAddressExample exampleAddress = new UserAddressExample();
        UserAddressExample.Criteria criteria2 = exampleAddress.createCriteria();
        criteria2.andUserIdEqualTo(userInfo.getId());
        criteria2.andStatusEqualTo(1);
        List<UserAddress> userAddresses = userAddressMapper.selectByExample(exampleAddress);
        if (userAddresses.size() > 0) {
            userAddress = userAddresses.get(0);
        }
        order.setId(System.currentTimeMillis() + RandomUtils.getRandomNumber(6));
        List<Long> feeList = new ArrayList<>();
        if (orderDtoList.length > 0) {
            for (OrderDto orderDto : orderDtoList) {

                GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderDto.getGoodsSku().getId());

                Goods goods = goodsMapper.selectByPrimaryKey(goodsSku.getGoodsId());

                if (goods.getStatus() == 0) {
                    result.setCode(500);
                    result.setError_description("商品已下架");
                    return result;
                }
                feeList.add(goods.getFreight());

                OrderItem orderItem = new OrderItem();
                orderItem.setGoodsId(goodsSku.getGoodsId());
                if (goodsSku.getInventory() > 0) {
                    goodsSku.setInventory(goodsSku.getInventory() - orderDto.getNum());
                    goodsSku.setSale(goodsSku.getSale() + orderDto.getNum());

                    //Goods goods1 = goodsMapper.selectByPrimaryKey(goodsSku.getGoodsId());
                    orderItem.setName(orderDto.getName() + "" + goodsSku.getSkuName());
                    // orderItem.setSkuName(goodsSku.getSkuName());
                    orderItem.setGoodsName(orderDto.getName());
                    orderItem.setNumber(orderDto.getNum());
                    SpikeGoodsExample example2 = new SpikeGoodsExample();
                    SpikeGoodsExample.Criteria criteria3 = example2.createCriteria();
                    criteria3.andSkuIdEqualTo(goodsSku.getId());
                    List<SpikeGoods> spikeGoods = spikeGoodsMapper.selectByExample(example2);
                    SpikeGoods spikeGoods1 = null;
                    if (spikeGoods.size() > 0) {
                        spikeGoods1 = spikeGoods.get(0);
                        if (new Date().getTime() > spikeGoods1.getBeginDate().getTime() && new Date().getTime() < spikeGoods1.getEndDate().getTime()) {
                            if (spikeGoods1.getSpikeNum() > orderDto.getNum()) {
                                result.setCode(500);
                                result.setError_description("超出限购数量");
                                return result;
                            }
                            if ((spikeGoods1.getMaxNum() - orderDto.getNum()) > 0) {
                                totalFee = (spikeGoods1.getSpikePrice() * orderDto.getNum());
                                orderFee += totalFee;
                                orderItem.setPrice(spikeGoods1.getSpikePrice());
                                order.setOrderStatus(1);
                                order.setOrderPrice(orderFee + Collections.max(feeList));
                                order.setGoodsFee(orderFee);
                            } else {
                                result.setCode(500);
                                result.setError_description("超出秒杀数量");
                                return result;
                            }

                        }
                    }
                    if ("INTEGRAL".equals(goods.getGoodsType())) {
                        totalFee = goods.getFreight();
                        orderFee += totalFee;
                        orderItem.setPrice(goodsSku.getPresentPrice());
                        order.setOrderPrice(orderFee);
                        order.setOrderStatus(1);
                        order.setIntegral(goodsSku.getConsumptionIntegral());

                    }
                    if ("ORDINARY".equals(goods.getGoodsType())) {
                        totalFee = (goodsSku.getPresentPrice() * orderDto.getNum());
                        orderFee += totalFee;
                        orderItem.setPrice(goodsSku.getPresentPrice());
                        order.setOrderStatus(1);
                        order.setOrderPrice(orderFee + Collections.max(feeList));
                        order.setGoodsFee(orderFee);
                    }
                    orderItem.setPrice(goodsSku.getPresentPrice());
                    orderItem.setOrderId(order.getId());
                    orderItem.setShopId(shopId);
                    orderItem.setSkuAttrId(orderDto.getGoodsSku().getId());
                    orderItemMapper.insert(orderItem);
                } else {
                    result.setCode(500);
                    result.setError_description("商品已售馨");
                    return result;
                }
                goodsSkuMapper.updateByPrimaryKey(goodsSku);

                if (goods.getInventory() > 0) {
                    goods.setInventory(goods.getInventory() - orderDto.getNum());
                    goods.setSalesVolume(goods.getSalesVolume() + orderDto.getNum());
                } else {
                    result.setCode(500);
                    result.setError_description("商品已售馨");
                    return result;
                }
                goodsMapper.updateByPrimaryKey(goods);
                solrService.releaseGoods(goods.getId(), shopId);

            }
            order.setUserId(userInfo.getId());
            order.setShopId(shopId);
            order.setStatus(0);

            if (BeanUtils.isNotEmpty(userAddress)) {
                order.setDeliveryId(userAddress.getId());
            } else {
                result.setCode(500);
                result.setError_description("请选择地址");
                return result;
            }
            order.setCreateDate(new Date());
            order.setFreight(Collections.max(feeList));

            orderMapper.insert(order);
        }
        result.setData(order.getId());
        return result;
    }

    @Override
    public ResponseResult showOrder(String orderId, String openId, Integer shopId) {
        Map map = new HashMap();
        UserInfoExample exampleInfo = new UserInfoExample();
        UserInfoExample.Criteria criteria1 = exampleInfo.createCriteria();
        criteria1.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(exampleInfo);
        List<UserAddress> userAddresses1 = new ArrayList<>();
        //List<UserAddress> userAddresses = new ArrayList<>();
        UserAddress userAddress = null;
        if (userInfos.size() > 0) {
            UserInfo userInfo = userInfos.get(0);
            UserAddressExample exampleAddress = new UserAddressExample();
            UserAddressExample.Criteria criteria = exampleAddress.createCriteria();
            criteria.andUserIdEqualTo(userInfo.getId());
            criteria.andStatusEqualTo(1);
            userAddresses1 = userAddressMapper.selectByExample(exampleAddress);

           /* UserAddressExample exampleAddress1 = new UserAddressExample();
            UserAddressExample.Criteria criteria2 = exampleAddress.createCriteria();
            criteria2.andUserIdEqualTo(userInfo.getId());
            criteria2.andStatusEqualTo(0);
            userAddresses = userAddressMapper.selectByExample(exampleAddress1);
            if (userAddresses.size() > 0) {
                userAddress = userAddresses.get(0);
            }*/
        }
        GoodsOrder order = orderMapper.selectByPrimaryKey(orderId);
        order.setFormartDate(order.getCreateDate().getTime());
        OrderItemExample example = new OrderItemExample();
        OrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        criteria.andShopIdEqualTo(shopId);
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
        for (OrderItem orderItem : orderItems) {
            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
            orderItem.setImage(goodsSku.getGoodsImage());
            orderItem.setSkuName(goodsSku.getSkuName());
            /*Goods goods = goodsMapper.selectByPrimaryKey(goodsSku.getGoodsId());
            order.setFreight(goods.getFreight());
            order.setGoodsFee(orderItem.getPrice() * orderItem.getNumber());*/

        }
        map.put("userAddresses1", userAddresses1);
        //map.put("userAddress", userAddress);
        map.put("order", order);
        map.put("orderItems", orderItems);
        ResponseResult result = new ResponseResult();
        result.setData(map);
        return result;
    }

    @Override
    public ResponseResult updateOrder(OrderDto orderDto) {
        orderMapper.updateByPrimaryKey(orderDto.getOrder());
        userAddressMapper.updateByPrimaryKey(orderDto.getUserAddress());
        ResponseResult result = new ResponseResult();
        return result;
    }

    @Override
    public ResponseResult manageOrder(Integer status, String openId, Integer shopId, String condition) {
        ResponseResult result = new ResponseResult();
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        if (userInfos.size() > 0) {
            userInfo = userInfos.get(0);
        }
        List list = new ArrayList();
        //待支付
        if (status != null) {
            if (status == 1) {
                GoodsOrderExample example1 = new GoodsOrderExample();
                GoodsOrderExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andUserIdEqualTo(userInfo.getId());
                criteria1.andOrderStatusEqualTo(1);
                criteria1.andShopIdEqualTo(shopId);
                List<GoodsOrder> goodsOrders1 = orderMapper.selectByExample(example1);
                List<String> orderIds1 = new ArrayList<>();
                for (GoodsOrder goodsOrder : goodsOrders1) {
                    orderIds1.add(goodsOrder.getId());
                }

                System.out.println(condition);
                if (BeanUtils.isNotEmpty(condition)) {
                    OrderItemExample orderExample1 = new OrderItemExample();
                    OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                    criteria2.andNameLike("%" + condition + "%");
                    criteria2.andShopIdEqualTo(shopId);
                    List<OrderItem> orderItems = orderItemMapper.selectByExample(orderExample1);
                    //Set<Integer> Idset = new HashSet();
                    List<GoodsOrder> list2 = new ArrayList<>();
                    Set<String> orderIds2 = new HashSet<>();
                    for (OrderItem orderItem : orderItems) {
                        GoodsOrderExample example2 = new GoodsOrderExample();
                        GoodsOrderExample.Criteria criteria3 = example2.createCriteria();
                        criteria3.andUserIdEqualTo(userInfo.getId());
                        criteria3.andIdEqualTo(orderItem.getOrderId());
                        criteria3.andShopIdEqualTo(shopId);
                        List<GoodsOrder> goodsOrders2 = orderMapper.selectByExample(example2);
                        list2.addAll(goodsOrders2);
                    }
                    for (GoodsOrder goodsOrder : list2) {
                        orderIds2.add(goodsOrder.getId());
                    }
                    List<String> intersection = orderIds1.stream().filter(item -> orderIds2.contains(item)).collect(toList());
                    List<GoodsOrder> orderList = new ArrayList();
                    for (String str : intersection) {
                        GoodsOrder goodsOrder = orderMapper.selectByPrimaryKey(str);
                        orderList.add(goodsOrder);
                    }
                    //System.out.println(orderList);
                    for (GoodsOrder goodsOrder : orderList) {
                        OrderItemExample orderExample2 = new OrderItemExample();
                        OrderItemExample.Criteria criteria3 = orderExample2.createCriteria();
                        criteria3.andOrderIdEqualTo(goodsOrder.getId());
                        List<OrderItem> orderItems1 = orderItemMapper.selectByExample(orderExample2);
                        List list1 = new ArrayList();
                        for (OrderItem orderItem : orderItems1) {
                            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                            Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                            Map map1 = new HashMap();
                            map1.put("goodsSku1", goodsSku);
                            map1.put("goods1", goods);
                            map1.put("orderItem", orderItem);
                            list1.add(map1);
                        }
                        Map map = new HashMap();
                        map.put("orderItems", list1);
                        map.put("order", goodsOrder);
                        list.add(map);
                    }
                } else {
                    for (GoodsOrder goodsOrder : goodsOrders1) {
                        OrderItemExample orderExample1 = new OrderItemExample();
                        OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                        criteria2.andOrderIdEqualTo(goodsOrder.getId());
                        List<OrderItem> orderItems = orderItemMapper.selectByExample(orderExample1);
                        List list1 = new ArrayList();
                        for (OrderItem orderItem : orderItems) {
                            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                            Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                            Map map1 = new HashMap();
                            map1.put("goodsSku1", goodsSku);
                            map1.put("goods1", goods);
                            map1.put("orderItem", orderItem);
                            list1.add(map1);
                        }
                        Map map = new HashMap();
                        map.put("orderItems", list1);
                        map.put("order", goodsOrder);
                        list.add(map);
                    }
                }

                Collections.reverse(list);
                result.setData(list);
            }

            if (status == 3) {
                GoodsOrderExample example1 = new GoodsOrderExample();
                GoodsOrderExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andUserIdEqualTo(userInfo.getId());
                criteria1.andOrderStatusEqualTo(3);
                criteria1.andShopIdEqualTo(shopId);
                List<GoodsOrder> goodsOrders1 = orderMapper.selectByExample(example1);
                List<String> orderIds1 = new ArrayList<>();
                for (GoodsOrder goodsOrder : goodsOrders1) {
                    orderIds1.add(goodsOrder.getId());
                }
                if (BeanUtils.isNotEmpty(condition)) {
                    OrderItemExample orderExample1 = new OrderItemExample();
                    OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                    criteria2.andNameLike("%" + condition + "%");
                    criteria2.andShopIdEqualTo(shopId);

                    List<OrderItem> orderItems = orderItemMapper.selectByExample(orderExample1);
                    //Set<Integer> Idset = new HashSet();
                    List<GoodsOrder> list2 = new ArrayList<>();
                    Set<String> orderIds2 = new HashSet<>();
                    for (OrderItem orderItem : orderItems) {
                        GoodsOrderExample example2 = new GoodsOrderExample();
                        GoodsOrderExample.Criteria criteria3 = example2.createCriteria();
                        criteria3.andUserIdEqualTo(userInfo.getId());
                        criteria3.andIdEqualTo(orderItem.getOrderId());
                        criteria3.andShopIdEqualTo(shopId);
                        List<GoodsOrder> goodsOrders2 = orderMapper.selectByExample(example2);
                        list2.addAll(goodsOrders2);
                    }
                    for (GoodsOrder goodsOrder : list2) {
                        orderIds2.add(goodsOrder.getId());
                    }
                    List<String> intersection = orderIds1.stream().filter(item -> orderIds2.contains(item)).collect(toList());
                    List<GoodsOrder> orderList = new ArrayList();
                    for (String str : intersection) {
                        GoodsOrder goodsOrder = orderMapper.selectByPrimaryKey(str);
                        orderList.add(goodsOrder);
                    }
                    //System.out.println(orderList);
                    for (GoodsOrder goodsOrder : orderList) {
                        OrderItemExample orderExample2 = new OrderItemExample();
                        OrderItemExample.Criteria criteria3 = orderExample2.createCriteria();
                        criteria3.andOrderIdEqualTo(goodsOrder.getId());
                        List<OrderItem> orderItems1 = orderItemMapper.selectByExample(orderExample2);
                        List list1 = new ArrayList();
                        for (OrderItem orderItem : orderItems1) {
                            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                            Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                            Map map1 = new HashMap();
                            map1.put("goodsSku1", goodsSku);
                            map1.put("goods1", goods);
                            map1.put("orderItem", orderItem);
                            list1.add(map1);
                        }
                        Map map = new HashMap();
                        map.put("orderItems", list1);
                        map.put("order", goodsOrder);
                        list.add(map);
                    }
                } else {
                    for (GoodsOrder goodsOrder : goodsOrders1) {
                        OrderItemExample orderExample1 = new OrderItemExample();
                        OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                        criteria2.andOrderIdEqualTo(goodsOrder.getId());

                        List<OrderItem> orderItems = orderItemMapper.selectByExample(orderExample1);
                        List list1 = new ArrayList();
                        for (OrderItem orderItem : orderItems) {
                            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                            Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                            Map map1 = new HashMap();
                            map1.put("goodsSku1", goodsSku);
                            map1.put("goods1", goods);
                            map1.put("orderItem", orderItem);
                            list1.add(map1);
                        }
                        Map map = new HashMap();
                        map.put("orderItems", list1);
                        map.put("order", goodsOrder);
                        list.add(map);
                    }
                }

                Collections.reverse(list);
                result.setData(list);
            }
            if (status == 5) {
                GoodsOrderExample example1 = new GoodsOrderExample();
                GoodsOrderExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andUserIdEqualTo(userInfo.getId());
                criteria1.andOrderStatusEqualTo(5);
                criteria1.andShopIdEqualTo(shopId);
                List<GoodsOrder> goodsOrders1 = orderMapper.selectByExample(example1);
                List<String> orderIds1 = new ArrayList<>();
                for (GoodsOrder goodsOrder : goodsOrders1) {
                    orderIds1.add(goodsOrder.getId());
                }
                if (BeanUtils.isNotEmpty(condition)) {
                    OrderItemExample orderExample1 = new OrderItemExample();
                    OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                    criteria2.andNameLike("%" + condition + "%");
                    criteria2.andShopIdEqualTo(shopId);

                    List<OrderItem> orderItems = orderItemMapper.selectByExample(orderExample1);
                    //Set<Integer> Idset = new HashSet();
                    List<GoodsOrder> list2 = new ArrayList<>();
                    Set<String> orderIds2 = new HashSet<>();
                    for (OrderItem orderItem : orderItems) {
                        GoodsOrderExample example2 = new GoodsOrderExample();
                        GoodsOrderExample.Criteria criteria3 = example2.createCriteria();
                        criteria3.andUserIdEqualTo(userInfo.getId());
                        criteria3.andIdEqualTo(orderItem.getOrderId());
                        criteria3.andShopIdEqualTo(shopId);
                        List<GoodsOrder> goodsOrders2 = orderMapper.selectByExample(example2);
                        list2.addAll(goodsOrders2);
                    }
                    for (GoodsOrder goodsOrder : list2) {
                        orderIds2.add(goodsOrder.getId());
                    }
                    List<String> intersection = orderIds1.stream().filter(item -> orderIds2.contains(item)).collect(toList());
                    List<GoodsOrder> orderList = new ArrayList();
                    for (String str : intersection) {
                        GoodsOrder goodsOrder = orderMapper.selectByPrimaryKey(str);
                        orderList.add(goodsOrder);
                    }
                    //System.out.println(orderList);
                    for (GoodsOrder goodsOrder : orderList) {
                        OrderItemExample orderExample2 = new OrderItemExample();
                        OrderItemExample.Criteria criteria3 = orderExample2.createCriteria();
                        criteria3.andOrderIdEqualTo(goodsOrder.getId());
                        List<OrderItem> orderItems1 = orderItemMapper.selectByExample(orderExample2);
                        List list1 = new ArrayList();
                        for (OrderItem orderItem : orderItems1) {
                            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                            Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                            Map map1 = new HashMap();
                            map1.put("goodsSku1", goodsSku);
                            map1.put("goods1", goods);
                            map1.put("orderItem", orderItem);
                            list1.add(map1);
                        }
                        Map map = new HashMap();
                        map.put("orderItems", list1);
                        map.put("order", goodsOrder);
                        list.add(map);
                    }
                } else {
                    for (GoodsOrder goodsOrder : goodsOrders1) {
                        OrderItemExample orderExample1 = new OrderItemExample();
                        OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                        criteria2.andOrderIdEqualTo(goodsOrder.getId());

                        List<OrderItem> orderItems = orderItemMapper.selectByExample(orderExample1);
                        List list1 = new ArrayList();
                        for (OrderItem orderItem : orderItems) {
                            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                            Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                            Map map1 = new HashMap();
                            map1.put("goodsSku1", goodsSku);
                            map1.put("goods1", goods);
                            map1.put("orderItem", orderItem);
                            list1.add(map1);
                        }
                        Map map = new HashMap();
                        map.put("orderItems", list1);
                        map.put("order", goodsOrder);
                        list.add(map);
                    }
                }

                Collections.reverse(list);
                result.setData(list);
            }

            if (status == 7) {
                GoodsOrderExample example1 = new GoodsOrderExample();
                GoodsOrderExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andUserIdEqualTo(userInfo.getId());
                criteria1.andOrderStatusEqualTo(7);
                criteria1.andShopIdEqualTo(shopId);
                List<GoodsOrder> goodsOrders1 = orderMapper.selectByExample(example1);
                List<String> orderIds1 = new ArrayList<>();
                for (GoodsOrder goodsOrder : goodsOrders1) {
                    orderIds1.add(goodsOrder.getId());
                }
                if (BeanUtils.isNotEmpty(condition)) {
                    OrderItemExample orderExample1 = new OrderItemExample();
                    OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                    criteria2.andNameLike("%" + condition + "%");
                    criteria2.andShopIdEqualTo(shopId);

                    List<OrderItem> orderItems = orderItemMapper.selectByExample(orderExample1);
                    //Set<Integer> Idset = new HashSet();
                    List<GoodsOrder> list2 = new ArrayList<>();
                    Set<String> orderIds2 = new HashSet<>();
                    for (OrderItem orderItem : orderItems) {
                        GoodsOrderExample example2 = new GoodsOrderExample();
                        GoodsOrderExample.Criteria criteria3 = example2.createCriteria();
                        criteria3.andUserIdEqualTo(userInfo.getId());
                        criteria3.andIdEqualTo(orderItem.getOrderId());
                        criteria3.andShopIdEqualTo(shopId);
                        List<GoodsOrder> goodsOrders2 = orderMapper.selectByExample(example2);
                        list2.addAll(goodsOrders2);
                    }
                    for (GoodsOrder goodsOrder : list2) {
                        orderIds2.add(goodsOrder.getId());
                    }
                    List<String> intersection = orderIds1.stream().filter(item -> orderIds2.contains(item)).collect(toList());
                    List<GoodsOrder> orderList = new ArrayList();
                    for (String str : intersection) {
                        GoodsOrder goodsOrder = orderMapper.selectByPrimaryKey(str);
                        orderList.add(goodsOrder);
                    }
                    //System.out.println(orderList);
                    for (GoodsOrder goodsOrder : orderList) {
                        OrderItemExample orderExample2 = new OrderItemExample();
                        OrderItemExample.Criteria criteria3 = orderExample2.createCriteria();
                        criteria3.andOrderIdEqualTo(goodsOrder.getId());
                        List<OrderItem> orderItems1 = orderItemMapper.selectByExample(orderExample2);
                        List list1 = new ArrayList();
                        for (OrderItem orderItem : orderItems1) {
                            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                            Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                            Map map1 = new HashMap();
                            map1.put("goodsSku1", goodsSku);
                            map1.put("goods1", goods);
                            map1.put("orderItem", orderItem);
                            list1.add(map1);
                        }
                        Map map = new HashMap();
                        map.put("orderItems", list1);
                        map.put("order", goodsOrder);
                        list.add(map);
                    }
                } else {
                    for (GoodsOrder goodsOrder : goodsOrders1) {
                        OrderItemExample orderExample1 = new OrderItemExample();
                        OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                        criteria2.andOrderIdEqualTo(goodsOrder.getId());

                        List<OrderItem> orderItems = orderItemMapper.selectByExample(orderExample1);
                        List list1 = new ArrayList();
                        for (OrderItem orderItem : orderItems) {
                            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                            Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                            Map map1 = new HashMap();
                            map1.put("goodsSku1", goodsSku);
                            map1.put("goods1", goods);
                            map1.put("orderItem", orderItem);
                            list1.add(map1);
                        }
                        Map map = new HashMap();
                        map.put("orderItems", list1);
                        map.put("order", goodsOrder);
                        list.add(map);
                    }
                }

                Collections.reverse(list);
                result.setData(list);
            }

            if (status == 9) {
                GoodsOrderExample example1 = new GoodsOrderExample();
                GoodsOrderExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andUserIdEqualTo(userInfo.getId());
                criteria1.andOrderStatusEqualTo(9);
                criteria1.andShopIdEqualTo(shopId);
                List<GoodsOrder> goodsOrders1 = orderMapper.selectByExample(example1);
                List<String> orderIds1 = new ArrayList<>();
                for (GoodsOrder goodsOrder : goodsOrders1) {
                    orderIds1.add(goodsOrder.getId());
                }
                if (BeanUtils.isNotEmpty(condition)) {
                    OrderItemExample orderExample1 = new OrderItemExample();
                    OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                    criteria2.andNameLike("%" + condition + "%");
                    criteria2.andShopIdEqualTo(shopId);
                    List<OrderItem> orderItems = orderItemMapper.selectByExample(orderExample1);
                    //Set<Integer> Idset = new HashSet();
                    List<GoodsOrder> list2 = new ArrayList<>();
                    Set<String> orderIds2 = new HashSet<>();
                    for (OrderItem orderItem : orderItems) {
                        GoodsOrderExample example2 = new GoodsOrderExample();
                        GoodsOrderExample.Criteria criteria3 = example2.createCriteria();
                        criteria3.andUserIdEqualTo(userInfo.getId());
                        criteria3.andIdEqualTo(orderItem.getOrderId());
                        criteria3.andShopIdEqualTo(shopId);
                        List<GoodsOrder> goodsOrders2 = orderMapper.selectByExample(example2);
                        list2.addAll(goodsOrders2);
                    }
                    for (GoodsOrder goodsOrder : list2) {
                        orderIds2.add(goodsOrder.getId());
                    }
                    List<String> intersection = orderIds1.stream().filter(item -> orderIds2.contains(item)).collect(toList());
                    List<GoodsOrder> orderList = new ArrayList();
                    for (String str : intersection) {
                        GoodsOrder goodsOrder = orderMapper.selectByPrimaryKey(str);
                        orderList.add(goodsOrder);
                    }
                    //System.out.println(orderList);
                    for (GoodsOrder goodsOrder : orderList) {
                        OrderItemExample orderExample2 = new OrderItemExample();
                        OrderItemExample.Criteria criteria3 = orderExample2.createCriteria();
                        criteria3.andOrderIdEqualTo(goodsOrder.getId());
                        List<OrderItem> orderItems1 = orderItemMapper.selectByExample(orderExample2);
                        List list1 = new ArrayList();
                        for (OrderItem orderItem : orderItems1) {
                            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                            Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                            Map map1 = new HashMap();
                            map1.put("goodsSku1", goodsSku);
                            map1.put("goods1", goods);
                            map1.put("orderItem", orderItem);
                            list1.add(map1);
                        }
                        Map map = new HashMap();
                        map.put("orderItems", list1);
                        map.put("order", goodsOrder);
                        list.add(map);
                    }
                } else {
                    for (GoodsOrder goodsOrder : goodsOrders1) {
                        OrderItemExample orderExample1 = new OrderItemExample();
                        OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                        criteria2.andOrderIdEqualTo(goodsOrder.getId());
                        List<OrderItem> orderItems = orderItemMapper.selectByExample(orderExample1);
                        List list1 = new ArrayList();
                        for (OrderItem orderItem : orderItems) {
                            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                            Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                            Map map1 = new HashMap();
                            map1.put("goodsSku1", goodsSku);
                            map1.put("goods1", goods);
                            map1.put("orderItem", orderItem);
                            list1.add(map1);
                        }
                        Map map = new HashMap();
                        map.put("orderItems", list1);
                        map.put("order", goodsOrder);
                        list.add(map);
                    }
                }
                Collections.reverse(list);
                result.setData(list);
            }
        }

        return result;
    }

    @Override
    public ResponseResult findAll(String openId, Integer shopId, String condition) {
        //PageHelper.startPage(pageNum,pageSize);
        ResponseResult result = new ResponseResult();
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        if (userInfos.size() > 0) {
            userInfo = userInfos.get(0);
        }
        // System.out.println(userInfo);
        List list = new ArrayList();

        if (BeanUtils.isNotEmpty(condition)) {
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(condition);
            if (isNum.matches()) {
                GoodsOrderExample example3 = new GoodsOrderExample();
                GoodsOrderExample.Criteria criteria3 = example3.createCriteria();
                criteria3.andUserIdEqualTo(userInfo.getId());
                criteria3.andIdEqualTo(condition);
                criteria3.andShopIdEqualTo(shopId);

                List<GoodsOrder> goodsOrders3 = orderMapper.selectByExample(example3);
                for (GoodsOrder goodsOrder : goodsOrders3) {
                    OrderItemExample orderExample1 = new OrderItemExample();
                    OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                    criteria2.andOrderIdEqualTo(goodsOrder.getId());
                    List<OrderItem> orderItems = orderItemMapper.selectByExample(orderExample1);
                    List list1 = new ArrayList();
                    for (OrderItem orderItem : orderItems) {
                        GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                        Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                        Map map1 = new HashMap();
                        map1.put("goodsSku1", goodsSku);
                        map1.put("goods1", goods);
                        map1.put("orderItem", orderItem);
                        list1.add(map1);
                    }
                    Map map = new HashMap();
                    map.put("orderItems", list1);
                    map.put("order", goodsOrder);
                    list.add(map);
                }
                Collections.reverse(list);
                result.setData(list);
            } else {
                OrderItemExample example3 = new OrderItemExample();
                OrderItemExample.Criteria criteria3 = example3.createCriteria();
                criteria3.andNameLike("%" + condition + "%");
                criteria3.andShopIdEqualTo(shopId);
                List<OrderItem> orderItems = orderItemMapper.selectByExample(example3);
                Set<GoodsOrder> set = new HashSet<>();
                for (OrderItem orderItem : orderItems) {
                    GoodsOrder goodsOrder = orderMapper.selectByPrimaryKey(orderItem.getOrderId());
                    if (BeanUtils.isNotEmpty(goodsOrder)) {
                        set.add(goodsOrder);
                    }
                }
                Integer userId = userInfo.getId();
                set.stream().filter(item -> item.getUserId() == userId).collect(toList());

                if (set.size() > 0) {
                    for (GoodsOrder goodsOrder : set) {
                        OrderItemExample orderExample1 = new OrderItemExample();
                        OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                        criteria2.andOrderIdEqualTo(goodsOrder.getId());
                        List<OrderItem> orderItems1 = orderItemMapper.selectByExample(orderExample1);
                        List list2 = new ArrayList();
                        for (OrderItem orderItem : orderItems1) {
                            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                            Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                            Map map1 = new HashMap();
                            map1.put("goodsSku1", goodsSku);
                            map1.put("goods1", goods);
                            map1.put("orderItem", orderItem);
                            list2.add(map1);
                        }
                        Map map = new HashMap();
                        map.put("orderItems", list2);
                        map.put("order", goodsOrder);
                        list.add(map);
                    }
                }

                Collections.reverse(list);
                result.setData(list);
            }

        } else {
            GoodsOrderExample example1 = new GoodsOrderExample();
            GoodsOrderExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andUserIdEqualTo(userInfo.getId());
            criteria1.andShopIdEqualTo(shopId);
            List<GoodsOrder> goodsOrders1 = orderMapper.selectByExample(example1);
            for (GoodsOrder goodsOrder : goodsOrders1) {
                OrderItemExample orderExample1 = new OrderItemExample();
                OrderItemExample.Criteria criteria2 = orderExample1.createCriteria();
                criteria2.andOrderIdEqualTo(goodsOrder.getId());
                List<OrderItem> orderItems = orderItemMapper.selectByExample(orderExample1);
                List list1 = new ArrayList();
                for (OrderItem orderItem : orderItems) {
                    GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                    Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                    Map map1 = new HashMap();
                    map1.put("goodsSku1", goodsSku);
                    map1.put("goods1", goods);
                    map1.put("orderItem", orderItem);
                    list1.add(map1);
                }
                Map map = new HashMap();
                map.put("orderItems", list1);
                map.put("order", goodsOrder);
                list.add(map);
            }
            //PageInfo<Map> page = new PageInfo<>(list);
            Collections.reverse(list);
        }
        result.setData(list);

        return result;
    }

    @Override
    public ResponseResult deleOrderById(String orderId, Integer shopId) {
        OrderItemExample example = new OrderItemExample();
        OrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
        for (OrderItem orderItem : orderItems) {
            GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
            goodsSku.setInventory(goodsSku.getInventory() + orderItem.getNumber());
            goodsSku.setSale(goodsSku.getSale() - orderItem.getNumber());
            goodsSkuMapper.updateByPrimaryKey(goodsSku);
            Goods goods = goodsMapper.selectByPrimaryKey(goodsSku.getGoodsId());
            goods.setInventory(goods.getInventory() + orderItem.getNumber());
            goods.setSalesVolume(goods.getSalesVolume() - orderItem.getNumber());
            goodsMapper.updateByPrimaryKey(goods);
            solrService.releaseGoods(goods.getId(), shopId);
        }
        orderMapper.deleteByPrimaryKey(orderId);

        ResponseResult result = new ResponseResult();
        return result;
    }

    @Override
    public ResponseResult updateStatus(String orderId) {
        GoodsOrder goodsOrder = orderMapper.selectByPrimaryKey(orderId);
        if (BeanUtils.isEmpty(goodsOrder.getIntegral())){
            goodsOrder.setOrderStatus(7);
        }
        goodsOrder.setOrderStatus(9);
        goodsOrder.setDeliveryDate(new Date());
        orderMapper.updateByPrimaryKey(goodsOrder);
        ResponseResult result = new ResponseResult();
        return result;
    }

    @Override
    public ResponseResult orderCount(Integer status, String openId, Integer shopId) {
        ResponseResult result = new ResponseResult();
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        if (userInfos.size() > 0) {
            userInfo = userInfos.get(0);
        }
        if (status == 1) {
            GoodsOrderExample example1 = new GoodsOrderExample();
            GoodsOrderExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andUserIdEqualTo(userInfo.getId());
            criteria1.andOrderStatusEqualTo(1);
            criteria1.andShopIdEqualTo(shopId);
            List<GoodsOrder> goodsOrders1 = orderMapper.selectByExample(example1);
            result.setData(goodsOrders1.size());
        }
        if (status == 3) {
            GoodsOrderExample example1 = new GoodsOrderExample();
            GoodsOrderExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andUserIdEqualTo(userInfo.getId());
            criteria1.andOrderStatusEqualTo(3);
            criteria1.andShopIdEqualTo(shopId);
            List<GoodsOrder> goodsOrders1 = orderMapper.selectByExample(example1);
            result.setData(goodsOrders1.size());
        }
        if (status == 5) {
            GoodsOrderExample example1 = new GoodsOrderExample();
            GoodsOrderExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andUserIdEqualTo(userInfo.getId());
            criteria1.andOrderStatusEqualTo(5);
            criteria1.andShopIdEqualTo(shopId);
            List<GoodsOrder> goodsOrders1 = orderMapper.selectByExample(example1);
            result.setData(goodsOrders1.size());
        }
        if (status == 7) {
            GoodsOrderExample example1 = new GoodsOrderExample();
            GoodsOrderExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andUserIdEqualTo(userInfo.getId());
            criteria1.andOrderStatusEqualTo(7);
            criteria1.andShopIdEqualTo(shopId);
            List<GoodsOrder> goodsOrders1 = orderMapper.selectByExample(example1);
            result.setData(goodsOrders1.size());
        }
        if (status == 9) {
            GoodsOrderExample example1 = new GoodsOrderExample();
            GoodsOrderExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andUserIdEqualTo(userInfo.getId());
            criteria1.andOrderStatusEqualTo(9);
            criteria1.andShopIdEqualTo(shopId);
            List<GoodsOrder> goodsOrders1 = orderMapper.selectByExample(example1);
            result.setData(goodsOrders1.size());
        }
        return result;
    }

    @Override
    public ResponseResult orderAllCount(String openId, Integer shopId) {
        ResponseResult result = new ResponseResult();
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        UserInfo userInfo = null;
        if (userInfos.size() > 0) {
            userInfo = userInfos.get(0);
        }
        GoodsOrderExample example1 = new GoodsOrderExample();
        GoodsOrderExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andUserIdEqualTo(userInfo.getId());
        criteria1.andShopIdEqualTo(shopId);
        List<GoodsOrder> goodsOrders1 = orderMapper.selectByExample(example1);
        result.setData(goodsOrders1.size());
        return result;
    }

    @Override
    public ResponseResult orderAddAddress(String orderId, Integer addressId) {
        GoodsOrder goodsOrder = orderMapper.selectByPrimaryKey(orderId);
        goodsOrder.setDeliveryId(addressId);
        orderMapper.updateByPrimaryKey(goodsOrder);
        ResponseResult result = new ResponseResult();
        return result;
    }

}
