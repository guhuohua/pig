package com.ch.dao;

import com.ch.entity.SysUser;
import com.ch.entity.SysUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    @Update("update sys_user set password = '@g0c211aa0bc22ccb@0g5F4*1fcf3@24c@#@2g1aa06a2dc20@66#*d0@4c2c216', salt = '0bdf8d16-159d-4ebe-8a93-506a14d1df2d' where id = #{userId} and shop_id = #{shopId}")
    int resetPassword(@Param("userId") Integer userId, @Param("shopId") Integer shopId);

}