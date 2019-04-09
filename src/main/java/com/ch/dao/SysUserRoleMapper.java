package com.ch.dao;

import com.ch.entity.SysUserRole;
import com.ch.entity.SysUserRoleExample;
import com.ch.entity.SysUserRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRoleMapper {
    int countByExample(SysUserRoleExample example);

    int deleteByExample(SysUserRoleExample example);

    int deleteByPrimaryKey(SysUserRoleKey key);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    List<SysUserRole> selectByExample(SysUserRoleExample example);

    SysUserRole selectByPrimaryKey(SysUserRoleKey key);

    int updateByExampleSelective(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

    int updateByExample(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    @Update("update sys_user_role set role_id = #{roleId} where user_id = #{userId} and shop_id = #{shopId}")
    int updateRole(@Param("roleId") Integer roleId, @Param("userId") Integer userId, @Param("shopId") Integer shopId);
}