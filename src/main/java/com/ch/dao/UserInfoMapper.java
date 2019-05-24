package com.ch.dao;

import com.ch.dao.provider.UserInfoProvider;
import com.ch.entity.UserInfo;
import com.ch.entity.UserInfoExample;
import java.util.List;

import com.ch.model.SysUserListDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {
    int countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    @SelectProvider(type = UserInfoProvider.class, method = "getList")
    List<SysUserListDTO> list(@Param("name") String name, @Param("tel") String tel, @Param("shopId") Integer shopId);


    @Select("select sum(order_price) from goods_order where user_id = #{userId} and shop_id = #{shopId} and order_status = 7")
    Long orderPrice(@Param("userId") Integer userId, @Param("shopId") Integer shopId);

    @Select("select count(*) from goods_order where user_id = #{userId} and shop_id = #{shopId} and order_status = 7")
    Long orderCount(@Param("userId") Integer userId, @Param("shopId") Integer shopId);
}