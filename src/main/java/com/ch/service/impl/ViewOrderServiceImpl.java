/**
 * Author: 常富文
 * Date:   2019/4/16 17:24
 * Description: 订单
 */


package com.ch.service.impl;

import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.dto.OrderDto;
import com.ch.entity.*;
import com.ch.service.ViewOrderService;
import com.ch.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        order.setId(System.currentTimeMillis() + RandomUtils.getRandomNumber(6));
        List<Long> feeList = new ArrayList<>();
        if (orderDtoList.length > 0) {
            for (OrderDto orderDto : orderDtoList) {
                GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderDto.getGoodsSku().getId());
                Goods goods = goodsMapper.selectByPrimaryKey(goodsSku.getGoodsId());
                feeList.add(goods.getFreight());
                GoodsExample example1 = new GoodsExample();
                GoodsExample.Criteria criteria1 = example1.createCriteria();

                goodsMapper.selectByExample(example1);
                if (goodsSku.getInventory() > 0) {
                    goodsSku.setInventory(goodsSku.getInventory() - 1);
                    goodsSku.setSale(goodsSku.getSale() + 1);
                } else {
                    result.setError("500");
                    result.setError_description("商品已售馨");
                    return result;
                }


                goodsSkuMapper.updateByPrimaryKey(goodsSku);
                totalFee = (goodsSku.getPresentPrice() * orderDto.getNum());
                orderFee += totalFee;
                OrderItem orderItem = new OrderItem();
                orderItem.setGoodsId(goodsSku.getGoodsId());

                if (goods.getInventory() > 0) {
                    goods.setInventory(goodsSku.getInventory() - 1);
                    goods.setSale(goodsSku.getSale() + 1);
                } else {
                    result.setError("500");
                    result.setError_description("商品已售馨");
                    return result;
                }

                goodsMapper.updateByPrimaryKey(goods);
                //Goods goods1 = goodsMapper.selectByPrimaryKey(goodsSku.getGoodsId());
                orderItem.setName(orderDto.getName());
                orderItem.setGoodsName(goodsSku.getSkuName());
                orderItem.setNumber(orderDto.getNum());
                orderItem.setPrice(goodsSku.getPresentPrice() * orderDto.getNum());
                orderItem.setOrderId(order.getId());
                orderItem.setShopId(shopId);
                orderItem.setSkuAttrId(orderDto.getGoodsSku().getId());

                orderItemMapper.insert(orderItem);
            }

            order.setUserId(userInfo.getId());
            order.setShopId(shopId);
            order.setOrderStatus(1);
            order.setStatus(0);
            order.setCreateDate(new Date());
            order.setOrderPrice(orderFee + Collections.max(feeList));
            orderMapper.insert(order);
        }


        result.setData(order.getId());
        return result;
    }

    @Override
    public ResponseResult showOrder(String orderId, String openId) {
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
        OrderItemExample example = new OrderItemExample();
        OrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);

        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);


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
    public ResponseResult manageOrder(Integer status, String openId, Integer shopId) {
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
                result.setData(list);
            }

            if (status == 3) {
                GoodsOrderExample example1 = new GoodsOrderExample();
                GoodsOrderExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andUserIdEqualTo(userInfo.getId());
                criteria1.andOrderStatusEqualTo(3);
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
                result.setData(list);
            }

            if (status == 7) {
                GoodsOrderExample example1 = new GoodsOrderExample();
                GoodsOrderExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andUserIdEqualTo(userInfo.getId());
                criteria1.andOrderStatusEqualTo(7);
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
                result.setData(list);
            }

            if (status == 9) {
                GoodsOrderExample example1 = new GoodsOrderExample();
                GoodsOrderExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andUserIdEqualTo(userInfo.getId());
                criteria1.andOrderStatusEqualTo(9);
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
                result.setData(list);
            }
        }

        return result;
    }

    @Override
    public ResponseResult findAll(String openId, Integer shopId) {
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
        List list = new ArrayList();

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
        result.setData(list);

        return result;
    }

    @Override
    public ResponseResult deleOrderById(String orderId) {
        orderMapper.deleteByPrimaryKey(orderId);
        ResponseResult result = new ResponseResult();
        return result;
    }

    @Override
    public ResponseResult updateStatus(String orderId) {
        GoodsOrder goodsOrder = orderMapper.selectByPrimaryKey(orderId);
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


}
