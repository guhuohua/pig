package com.ch.dao;

import com.ch.entity.SysPermission;
import com.ch.entity.SysPermissionExample;
import java.util.List;

import com.ch.model.RolePermissionModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SysPermissionMapper {
    int countByExample(SysPermissionExample example);

    int deleteByExample(SysPermissionExample example);

    int deleteByPrimaryKey(Integer permissionId);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    List<SysPermission> selectByExample(SysPermissionExample example);

    SysPermission selectByPrimaryKey(Integer permissionId);

    int updateByExampleSelective(@Param("record") SysPermission record, @Param("example") SysPermissionExample example);

    int updateByExample(@Param("record") SysPermission record, @Param("example") SysPermissionExample example);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    @Select("select permission_id, name, parent_id, `sort` from sys_permission")
    @Results({
            @Result(column = "permission_id", property = "permissionId", javaType = String.class),
            @Result(column = "name", property = "permissionName", javaType = String.class),
            @Result(column = "parent_id", property = "parentId", javaType = Integer.class),
            @Result(column = "sort", property = "sortOrder", javaType = Integer.class)
    })
    List<RolePermissionModel> findAll();
}