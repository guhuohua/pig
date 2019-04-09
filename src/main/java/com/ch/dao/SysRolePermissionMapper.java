package com.ch.dao;

import com.ch.entity.SysRolePermission;
import com.ch.entity.SysRolePermissionExample;
import java.util.List;

import com.ch.model.RolePermissionModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRolePermissionMapper {
    int countByExample(SysRolePermissionExample example);

    int deleteByExample(SysRolePermissionExample example);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    List<SysRolePermission> selectByExample(SysRolePermissionExample example);

    int updateByExampleSelective(@Param("record") SysRolePermission record, @Param("example") SysRolePermissionExample example);

    int updateByExample(@Param("record") SysRolePermission record, @Param("example") SysRolePermissionExample example);

    /**
     * 根据角色ID查询对应权限
     * @param roleId
     * @return
     */
    @Select("select bsrp.role_id,bsp.permission_id,bsp.name,bsp.parent_id,bsp.sort from sys_role_permission bsrp" +
            "  left join sys_permission bsp on bsp.permission_id = bsrp.permission_id where bsrp.role_id = #{roleId} and bsrp.shop_id = #{shopId}")
    @Results({
            @Result(column = "role_id", property = "roleId", javaType = String.class),
            @Result(column = "permission_id", property = "permissionId", javaType = String.class),
            @Result(column = "name", property = "permissionName", javaType = String.class),
            @Result(column = "parent_id", property = "parentId", javaType = Integer.class),
            @Result(column = "sort", property = "sortOrder", javaType = Integer.class)
    })
    List<RolePermissionModel> findAllByRoleId(@Param("roleId") Integer roleId, @Param("shopId") Integer shopId);
}