package com.ch.dao;

import com.ch.dao.provider.UserAddressProvider;
import com.ch.entity.UserAddress;
import com.ch.entity.UserAddressExample;
import java.util.List;

import com.ch.model.SysUserAddressModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressMapper {
    int countByExample(UserAddressExample example);

    int deleteByExample(UserAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    List<UserAddress> selectByExample(UserAddressExample example);

    UserAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    int updateByExample(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);


    @SelectProvider(type = UserAddressProvider.class, method = "getList")
    List<SysUserAddressModel> list(@Param("name") String name, @Param("tel") String tel, @Param("shopId") Integer shopId);
}