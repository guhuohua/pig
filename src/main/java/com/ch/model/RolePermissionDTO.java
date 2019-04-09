package com.ch.model;

import lombok.Data;

import java.util.List;

@Data
public class RolePermissionDTO {

    private Integer roleId;

    private List<Integer> permissions;
}
