package com.ch.dao;

import com.ch.entity.UserDeliveryAddress;
import com.ch.entity.UserDeliveryAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDeliveryAddressMapper {
    int countByExample(UserDeliveryAddressExample example);

    int deleteByExample(UserDeliveryAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserDeliveryAddress record);

    int insertSelective(UserDeliveryAddress record);

    List<UserDeliveryAddress> selectByExample(UserDeliveryAddressExample example);

    UserDeliveryAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserDeliveryAddress record, @Param("example") UserDeliveryAddressExample example);

    int updateByExample(@Param("record") UserDeliveryAddress record, @Param("example") UserDeliveryAddressExample example);

    int updateByPrimaryKeySelective(UserDeliveryAddress record);

    int updateByPrimaryKey(UserDeliveryAddress record);
}