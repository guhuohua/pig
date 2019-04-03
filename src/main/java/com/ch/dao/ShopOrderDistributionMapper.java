package com.ch.dao;

import com.ch.entity.ShopOrderDistribution;
import com.ch.entity.ShopOrderDistributionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopOrderDistributionMapper {
    int countByExample(ShopOrderDistributionExample example);

    int deleteByExample(ShopOrderDistributionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopOrderDistribution record);

    int insertSelective(ShopOrderDistribution record);

    List<ShopOrderDistribution> selectByExample(ShopOrderDistributionExample example);

    ShopOrderDistribution selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopOrderDistribution record, @Param("example") ShopOrderDistributionExample example);

    int updateByExample(@Param("record") ShopOrderDistribution record, @Param("example") ShopOrderDistributionExample example);

    int updateByPrimaryKeySelective(ShopOrderDistribution record);

    int updateByPrimaryKey(ShopOrderDistribution record);
}