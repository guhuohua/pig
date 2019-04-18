package com.ch.dao;

import com.ch.dao.provider.GoodsEvaluationProvider;
import com.ch.dto.SysEvaluateDTO;
import com.ch.entity.GoodsEvaluation;
import com.ch.entity.GoodsEvaluationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Repository
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

    /**
     * 最近一周的好评
     * @param shopId
     * @return
     */
    @Select("select count(*) from goods_evaluation where DATE_SUB(CURDATE(),INTERVAL 1 WEEK) <= DATE(create_time) and score = 5 and shop_id = #{shopId}")
    int goodsWeek(@Param("shopId") Integer shopId);

    /**
     * 最近一周的中评
     * @param shopId
     * @return
     */
    @Select("select count(*) from goods_evaluation where DATE_SUB(CURDATE(),INTERVAL 1 WEEK) <= DATE(create_time) and score in (3,4) and shop_id = #{shopId}")
    int secondaryWeek(@Param("shopId") Integer shopId);

    /**
     * 最近一周的差评
     * @param shopId
     * @return
     */
    @Select("select count(*) from goods_evaluation where DATE_SUB(CURDATE(),INTERVAL 1 WEEK) <= DATE(create_time) and score in (1,2) and shop_id = #{shopId}")
    int differenceWeek(@Param("shopId") Integer shopId);

    /**
     * 最近一个月的好评
     * @param shopId
     * @return
     */
    @Select("select count(*) from goods_evaluation where DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= DATE(create_time) and score = 5 and shop_id = #{shopId}")
    int goodsMonth(@Param("shopId") Integer shopId);

    /**
     * 最近一个月的中评
     * @param shopId
     * @return
     */
    @Select("select count(*) from goods_evaluation where DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= DATE(create_time) and score in (3,4) and shop_id = #{shopId}")
    int secondaryMonth(@Param("shopId") Integer shopId);

    /**
     * 最近一个月的差评
     * @param shopId
     * @return
     */
    @Select("select count(*) from goods_evaluation where DATE_SUB(CURDATE(),INTERVAL 1 MONTH) <= DATE(create_time) and score in (1,2) and shop_id = #{shopId}")
    int differenceMonth(@Param("shopId") Integer shopId);

    /**
     * 最近三个月的好评
     * @param shopId
     * @return
     */
    @Select("select count(*) from goods_evaluation where DATE_SUB(CURDATE(),INTERVAL 3 MONTH) <= DATE(create_time) and score = 5 and shop_id = #{shopId}")
    int goodsThreeMonth(@Param("shopId") Integer shopId);

    /**
     * 最近三个月的中评
     * @param shopId
     * @return
     */
    @Select("select count(*) from goods_evaluation where DATE_SUB(CURDATE(),INTERVAL 3 MONTH) <= DATE(create_time) and score in (3,4) and shop_id = #{shopId}")
    int secondaryThreeMonth(@Param("shopId") Integer shopId);

    /**
     * 最近三个月的差评
     * @param shopId
     * @return
     */
    @Select("select count(*) from goods_evaluation where DATE_SUB(CURDATE(),INTERVAL 3 MONTH) <= DATE(create_time) and score in (1,2) and shop_id = #{shopId}")
    int differenceThreeMonth(@Param("shopId") Integer shopId);

    /**
     * 全部好评
     * @param shopId
     * @return
     */
    @Select("select count(*) from goods_evaluation where score = 5 and shop_id = #{shopId}")
    int goodsCount(@Param("shopId") Integer shopId);

    /**
     * 全部中评
     * @param shopId
     * @return
     */
    @Select("select count(*) from goods_evaluation where score in (3,4) and shop_id = #{shopId}")
    int secondaryCount(@Param("shopId") Integer shopId);

    /**
     * 全部差评
     * @param shopId
     * @return
     */
    @Select("select count(*) from goods_evaluation where score in (1,2) and shop_id = #{shopId}")
    int differenceCount(@Param("shopId") Integer shopId);

    @SelectProvider(type = GoodsEvaluationProvider.class, method = "getList")
    List<SysEvaluateDTO> list(@Param("name") String name, @Param("score") Integer score, @Param("status") Integer status, @Param("shopId") Integer shopId);
}