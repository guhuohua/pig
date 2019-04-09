package com.ch.model;

import lombok.Data;

import java.util.List;

@Data
public class RolePermissionModel {
    private Integer roleId;

    private Integer permissionId;

    private String permissionName;

    private Integer parentId;

    private Integer sortOrder;

    private Integer checked; //0未选中，1选中

    private String label;

    private List<RolePermissionModel> children;
}
