package com.ch.dao;

import com.ch.entity.GoodsEvaluation;
import com.ch.entity.GoodsEvaluationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsEvaluationMapper {
    int countByExample(GoodsEvaluationExample example);

    int deleteByExample(GoodsEvaluationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsEvaluation record);

    int insertSelective(GoodsEvaluation record);

    List<GoodsEvaluation> selectByExample(GoodsEvaluationExample example);

    GoodsEvaluation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsEvaluation record, @Param("example") GoodsEvaluationExample example);

    int updateByExample(@Param("record") GoodsEvaluation record, @Param("example") GoodsEvaluationExample example);

    int updateByPrimaryKeySelective(GoodsEvaluation record);

    int updateByPrimaryKey(GoodsEvaluation record);
}