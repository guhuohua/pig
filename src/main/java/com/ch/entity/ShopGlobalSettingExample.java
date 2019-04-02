package com.ch.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopGlobalSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopGlobalSettingExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNull() {
            addCriterion("shop_id is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("shop_id is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(Integer value) {
            addCriterion("shop_id =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(Integer value) {
            addCriterion("shop_id <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(Integer value) {
            addCriterion("shop_id >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_id >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(Integer value) {
            addCriterion("shop_id <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Integer value) {
            addCriterion("shop_id <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<Integer> values) {
            addCriterion("shop_id in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<Integer> values) {
            addCriterion("shop_id not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(Integer value1, Integer value2) {
            addCriterion("shop_id between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_id not between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(Integer value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(Integer value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(Integer value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(Integer value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(Integer value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<Integer> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<Integer> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(Integer value1, Integer value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andIndexRecommendIsNull() {
            addCriterion("index_recommend is null");
            return (Criteria) this;
        }

        public Criteria andIndexRecommendIsNotNull() {
            addCriterion("index_recommend is not null");
            return (Criteria) this;
        }

        public Criteria andIndexRecommendEqualTo(String value) {
            addCriterion("index_recommend =", value, "indexRecommend");
            return (Criteria) this;
        }

        public Criteria andIndexRecommendNotEqualTo(String value) {
            addCriterion("index_recommend <>", value, "indexRecommend");
            return (Criteria) this;
        }

        public Criteria andIndexRecommendGreaterThan(String value) {
            addCriterion("index_recommend >", value, "indexRecommend");
            return (Criteria) this;
        }

        public Criteria andIndexRecommendGreaterThanOrEqualTo(String value) {
            addCriterion("index_recommend >=", value, "indexRecommend");
            return (Criteria) this;
        }

        public Criteria andIndexRecommendLessThan(String value) {
            addCriterion("index_recommend <", value, "indexRecommend");
            return (Criteria) this;
        }

        public Criteria andIndexRecommendLessThanOrEqualTo(String value) {
            addCriterion("index_recommend <=", value, "indexRecommend");
            return (Criteria) this;
        }

        public Criteria andIndexRecommendLike(String value) {
            addCriterion("index_recommend like", value, "indexRecommend");
            return (Criteria) this;
        }

        public Criteria andIndexRecommendNotLike(String value) {
            addCriterion("index_recommend not like", value, "indexRecommend");
            return (Criteria) this;
        }

        public Criteria andIndexRecommendIn(List<String> values) {
            addCriterion("index_recommend in", values, "indexRecommend");
            return (Criteria) this;
        }

        public Criteria andIndexRecommendNotIn(List<String> values) {
            addCriterion("index_recommend not in", values, "indexRecommend");
            return (Criteria) this;
        }

        public Criteria andIndexRecommendBetween(String value1, String value2) {
            addCriterion("index_recommend between", value1, value2, "indexRecommend");
            return (Criteria) this;
        }

        public Criteria andIndexRecommendNotBetween(String value1, String value2) {
            addCriterion("index_recommend not between", value1, value2, "indexRecommend");
            return (Criteria) this;
        }

        public Criteria andIndexCouponIsNull() {
            addCriterion("index_coupon is null");
            return (Criteria) this;
        }

        public Criteria andIndexCouponIsNotNull() {
            addCriterion("index_coupon is not null");
            return (Criteria) this;
        }

        public Criteria andIndexCouponEqualTo(String value) {
            addCriterion("index_coupon =", value, "indexCoupon");
            return (Criteria) this;
        }

        public Criteria andIndexCouponNotEqualTo(String value) {
            addCriterion("index_coupon <>", value, "indexCoupon");
            return (Criteria) this;
        }

        public Criteria andIndexCouponGreaterThan(String value) {
            addCriterion("index_coupon >", value, "indexCoupon");
            return (Criteria) this;
        }

        public Criteria andIndexCouponGreaterThanOrEqualTo(String value) {
            addCriterion("index_coupon >=", value, "indexCoupon");
            return (Criteria) this;
        }

        public Criteria andIndexCouponLessThan(String value) {
            addCriterion("index_coupon <", value, "indexCoupon");
            return (Criteria) this;
        }

        public Criteria andIndexCouponLessThanOrEqualTo(String value) {
            addCriterion("index_coupon <=", value, "indexCoupon");
            return (Criteria) this;
        }

        public Criteria andIndexCouponLike(String value) {
            addCriterion("index_coupon like", value, "indexCoupon");
            return (Criteria) this;
        }

        public Criteria andIndexCouponNotLike(String value) {
            addCriterion("index_coupon not like", value, "indexCoupon");
            return (Criteria) this;
        }

        public Criteria andIndexCouponIn(List<String> values) {
            addCriterion("index_coupon in", values, "indexCoupon");
            return (Criteria) this;
        }

        public Criteria andIndexCouponNotIn(List<String> values) {
            addCriterion("index_coupon not in", values, "indexCoupon");
            return (Criteria) this;
        }

        public Criteria andIndexCouponBetween(String value1, String value2) {
            addCriterion("index_coupon between", value1, value2, "indexCoupon");
            return (Criteria) this;
        }

        public Criteria andIndexCouponNotBetween(String value1, String value2) {
            addCriterion("index_coupon not between", value1, value2, "indexCoupon");
            return (Criteria) this;
        }

        public Criteria andShopcarRecommendIsNull() {
            addCriterion("shopcar_recommend is null");
            return (Criteria) this;
        }

        public Criteria andShopcarRecommendIsNotNull() {
            addCriterion("shopcar_recommend is not null");
            return (Criteria) this;
        }

        public Criteria andShopcarRecommendEqualTo(String value) {
            addCriterion("shopcar_recommend =", value, "shopcarRecommend");
            return (Criteria) this;
        }

        public Criteria andShopcarRecommendNotEqualTo(String value) {
            addCriterion("shopcar_recommend <>", value, "shopcarRecommend");
            return (Criteria) this;
        }

        public Criteria andShopcarRecommendGreaterThan(String value) {
            addCriterion("shopcar_recommend >", value, "shopcarRecommend");
            return (Criteria) this;
        }

        public Criteria andShopcarRecommendGreaterThanOrEqualTo(String value) {
            addCriterion("shopcar_recommend >=", value, "shopcarRecommend");
            return (Criteria) this;
        }

        public Criteria andShopcarRecommendLessThan(String value) {
            addCriterion("shopcar_recommend <", value, "shopcarRecommend");
            return (Criteria) this;
        }

        public Criteria andShopcarRecommendLessThanOrEqualTo(String value) {
            addCriterion("shopcar_recommend <=", value, "shopcarRecommend");
            return (Criteria) this;
        }

        public Criteria andShopcarRecommendLike(String value) {
            addCriterion("shopcar_recommend like", value, "shopcarRecommend");
            return (Criteria) this;
        }

        public Criteria andShopcarRecommendNotLike(String value) {
            addCriterion("shopcar_recommend not like", value, "shopcarRecommend");
            return (Criteria) this;
        }

        public Criteria andShopcarRecommendIn(List<String> values) {
            addCriterion("shopcar_recommend in", values, "shopcarRecommend");
            return (Criteria) this;
        }

        public Criteria andShopcarRecommendNotIn(List<String> values) {
            addCriterion("shopcar_recommend not in", values, "shopcarRecommend");
            return (Criteria) this;
        }

        public Criteria andShopcarRecommendBetween(String value1, String value2) {
            addCriterion("shopcar_recommend between", value1, value2, "shopcarRecommend");
            return (Criteria) this;
        }

        public Criteria andShopcarRecommendNotBetween(String value1, String value2) {
            addCriterion("shopcar_recommend not between", value1, value2, "shopcarRecommend");
            return (Criteria) this;
        }

        public Criteria andOrderRecommendIsNull() {
            addCriterion("order_recommend is null");
            return (Criteria) this;
        }

        public Criteria andOrderRecommendIsNotNull() {
            addCriterion("order_recommend is not null");
            return (Criteria) this;
        }

        public Criteria andOrderRecommendEqualTo(String value) {
            addCriterion("order_recommend =", value, "orderRecommend");
            return (Criteria) this;
        }

        public Criteria andOrderRecommendNotEqualTo(String value) {
            addCriterion("order_recommend <>", value, "orderRecommend");
            return (Criteria) this;
        }

        public Criteria andOrderRecommendGreaterThan(String value) {
            addCriterion("order_recommend >", value, "orderRecommend");
            return (Criteria) this;
        }

        public Criteria andOrderRecommendGreaterThanOrEqualTo(String value) {
            addCriterion("order_recommend >=", value, "orderRecommend");
            return (Criteria) this;
        }

        public Criteria andOrderRecommendLessThan(String value) {
            addCriterion("order_recommend <", value, "orderRecommend");
            return (Criteria) this;
        }

        public Criteria andOrderRecommendLessThanOrEqualTo(String value) {
            addCriterion("order_recommend <=", value, "orderRecommend");
            return (Criteria) this;
        }

        public Criteria andOrderRecommendLike(String value) {
            addCriterion("order_recommend like", value, "orderRecommend");
            return (Criteria) this;
        }

        public Criteria andOrderRecommendNotLike(String value) {
            addCriterion("order_recommend not like", value, "orderRecommend");
            return (Criteria) this;
        }

        public Criteria andOrderRecommendIn(List<String> values) {
            addCriterion("order_recommend in", values, "orderRecommend");
            return (Criteria) this;
        }

        public Criteria andOrderRecommendNotIn(List<String> values) {
            addCriterion("order_recommend not in", values, "orderRecommend");
            return (Criteria) this;
        }

        public Criteria andOrderRecommendBetween(String value1, String value2) {
            addCriterion("order_recommend between", value1, value2, "orderRecommend");
            return (Criteria) this;
        }

        public Criteria andOrderRecommendNotBetween(String value1, String value2) {
            addCriterion("order_recommend not between", value1, value2, "orderRecommend");
            return (Criteria) this;
        }

        public Criteria andSearchKeyIsNull() {
            addCriterion("search_key is null");
            return (Criteria) this;
        }

        public Criteria andSearchKeyIsNotNull() {
            addCriterion("search_key is not null");
            return (Criteria) this;
        }

        public Criteria andSearchKeyEqualTo(String value) {
            addCriterion("search_key =", value, "searchKey");
            return (Criteria) this;
        }

        public Criteria andSearchKeyNotEqualTo(String value) {
            addCriterion("search_key <>", value, "searchKey");
            return (Criteria) this;
        }

        public Criteria andSearchKeyGreaterThan(String value) {
            addCriterion("search_key >", value, "searchKey");
            return (Criteria) this;
        }

        public Criteria andSearchKeyGreaterThanOrEqualTo(String value) {
            addCriterion("search_key >=", value, "searchKey");
            return (Criteria) this;
        }

        public Criteria andSearchKeyLessThan(String value) {
            addCriterion("search_key <", value, "searchKey");
            return (Criteria) this;
        }

        public Criteria andSearchKeyLessThanOrEqualTo(String value) {
            addCriterion("search_key <=", value, "searchKey");
            return (Criteria) this;
        }

        public Criteria andSearchKeyLike(String value) {
            addCriterion("search_key like", value, "searchKey");
            return (Criteria) this;
        }

        public Criteria andSearchKeyNotLike(String value) {
            addCriterion("search_key not like", value, "searchKey");
            return (Criteria) this;
        }

        public Criteria andSearchKeyIn(List<String> values) {
            addCriterion("search_key in", values, "searchKey");
            return (Criteria) this;
        }

        public Criteria andSearchKeyNotIn(List<String> values) {
            addCriterion("search_key not in", values, "searchKey");
            return (Criteria) this;
        }

        public Criteria andSearchKeyBetween(String value1, String value2) {
            addCriterion("search_key between", value1, value2, "searchKey");
            return (Criteria) this;
        }

        public Criteria andSearchKeyNotBetween(String value1, String value2) {
            addCriterion("search_key not between", value1, value2, "searchKey");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}