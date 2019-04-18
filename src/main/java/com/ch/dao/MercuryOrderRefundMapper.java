package com.ch.dao;

import com.ch.entity.MercuryOrderRefund;
import com.ch.entity.MercuryOrderRefundExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MercuryOrderRefundMapper {
    int countByExample(MercuryOrderRefundExample example);

    int deleteByExample(MercuryOrderRefundExample example);

    int deleteByPrimaryKey(String id);

    int insert(MercuryOrderRefund record);

    int insertSelective(MercuryOrderRefund record);

    List<MercuryOrderRefund> selectByExample(MercuryOrderRefundExample example);

    MercuryOrderRefund selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MercuryOrderRefund record, @Param("example") MercuryOrderRefundExample example);

    int updateByExample(@Param("record") MercuryOrderRefund record, @Param("example") MercuryOrderRefundExample example);

    int updateByPrimaryKeySelective(MercuryOrderRefund record);

    int updateByPrimaryKey(MercuryOrderRefund record);
}