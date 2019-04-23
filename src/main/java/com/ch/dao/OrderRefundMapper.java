package com.ch.dao;

import com.ch.dao.provider.OrderRefundProvider;
import com.ch.dto.SysOrderRefundDTO;
import com.ch.entity.OrderRefund;
import com.ch.entity.OrderRefundExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRefundMapper {
    int countByExample(OrderRefundExample example);

    int deleteByExample(OrderRefundExample example);

    int deleteByPrimaryKey(String id);

    int insert(OrderRefund record);

    int insertSelective(OrderRefund record);

    List<OrderRefund> selectByExample(OrderRefundExample example);

    OrderRefund selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OrderRefund record, @Param("example") OrderRefundExample example);

    int updateByExample(@Param("record") OrderRefund record, @Param("example") OrderRefundExample example);

    int updateByPrimaryKeySelective(OrderRefund record);

    int updateByPrimaryKey(OrderRefund record);


    @SelectProvider(type = OrderRefundProvider.class, method = "getList")
    List<SysOrderRefundDTO> list(@Param("shopId") Integer shopId, @Param("name") String name,
                                 @Param("refundId") String refundId, @Param("refundStatus") Integer refundStatus);
}