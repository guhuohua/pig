package com.ch.model;

import lombok.Data;

import java.util.List;

@Data
public class SysBaseSettingParam {

    private List<MemberModel> memberModels;

    private Integer sign;

    private Integer perfect;

    private Integer comment;
}
