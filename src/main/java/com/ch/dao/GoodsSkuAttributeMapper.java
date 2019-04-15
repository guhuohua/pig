package com.ch.dao;

import com.ch.entity.GoodsSkuAttribute;
import com.ch.entity.GoodsSkuAttributeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsSkuAttributeMapper {
    int countByExample(GoodsSkuAttributeExample example);

    int deleteByExample(GoodsSkuAttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSkuAttribute record);

    int insertSelective(GoodsSkuAttribute record);

    List<GoodsSkuAttribute> selectByExample(GoodsSkuAttributeExample example);

    GoodsSkuAttribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsSkuAttribute record, @Param("example") GoodsSkuAttributeExample example);

    int updateByExample(@Param("record") GoodsSkuAttribute record, @Param("example") GoodsSkuAttributeExample example);

    int updateByPrimaryKeySelective(GoodsSkuAttribute record);

    int updateByPrimaryKey(GoodsSkuAttribute record);
}