package com.ch.model;

import com.ch.base.PageQuery;
import lombok.Data;

@Data
public class PersonMangeParam extends PageQuery {

    private String username;

    private String phone;

    private Integer userId;

    private Integer roleId;

    private String password;

    private Integer status;

}
