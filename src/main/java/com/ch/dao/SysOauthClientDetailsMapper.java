package com.ch.dao;

import com.ch.entity.SysOauthClientDetails;
import com.ch.entity.SysOauthClientDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOauthClientDetailsMapper {
    int countByExample(SysOauthClientDetailsExample example);

    int deleteByExample(SysOauthClientDetailsExample example);

    int deleteByPrimaryKey(String clientId);

    int insert(SysOauthClientDetails record);

    int insertSelective(SysOauthClientDetails record);

    List<SysOauthClientDetails> selectByExample(SysOauthClientDetailsExample example);

    SysOauthClientDetails selectByPrimaryKey(String clientId);

    int updateByExampleSelective(@Param("record") SysOauthClientDetails record, @Param("example") SysOauthClientDetailsExample example);

    int updateByExample(@Param("record") SysOauthClientDetails record, @Param("example") SysOauthClientDetailsExample example);

    int updateByPrimaryKeySelective(SysOauthClientDetails record);

    int updateByPrimaryKey(SysOauthClientDetails record);
}