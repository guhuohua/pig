package com.ch.model;

import lombok.Data;

@Data
public class SysUserListDTO {

    private Integer id;

    private String nickname;

    private String tel;

    private String member;

    private Integer integral;

    private Integer useIntegral;

    private Long invitation_code;

    private String superiorInvitationCode;
}
