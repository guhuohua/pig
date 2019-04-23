package com.ch.dao;

import com.ch.entity.PaySuccessInfo;
import com.ch.entity.PaySuccessInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaySuccessInfoMapper {
    int countByExample(PaySuccessInfoExample example);

    int deleteByExample(PaySuccessInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PaySuccessInfo record);

    int insertSelective(PaySuccessInfo record);

    List<PaySuccessInfo> selectByExample(PaySuccessInfoExample example);

    PaySuccessInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PaySuccessInfo record, @Param("example") PaySuccessInfoExample example);

    int updateByExample(@Param("record") PaySuccessInfo record, @Param("example") PaySuccessInfoExample example);

    int updateByPrimaryKeySelective(PaySuccessInfo record);

    int updateByPrimaryKey(PaySuccessInfo record);
}