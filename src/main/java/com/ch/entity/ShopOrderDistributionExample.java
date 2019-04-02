package com.ch.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopOrderDistributionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopOrderDistributionExample() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNull() {
            addCriterion("type_name is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("type_name is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("type_name =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("type_name <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("type_name >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("type_name >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("type_name <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("type_name <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("type_name like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("type_name not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("type_name in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("type_name not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("type_name between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("type_name not between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andFreeShippingIsNull() {
            addCriterion("free_shipping is null");
            return (Criteria) this;
        }

        public Criteria andFreeShippingIsNotNull() {
            addCriterion("free_shipping is not null");
            return (Criteria) this;
        }

        public Criteria andFreeShippingEqualTo(Integer value) {
            addCriterion("free_shipping =", value, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingNotEqualTo(Integer value) {
            addCriterion("free_shipping <>", value, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingGreaterThan(Integer value) {
            addCriterion("free_shipping >", value, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingGreaterThanOrEqualTo(Integer value) {
            addCriterion("free_shipping >=", value, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingLessThan(Integer value) {
            addCriterion("free_shipping <", value, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingLessThanOrEqualTo(Integer value) {
            addCriterion("free_shipping <=", value, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingIn(List<Integer> values) {
            addCriterion("free_shipping in", values, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingNotIn(List<Integer> values) {
            addCriterion("free_shipping not in", values, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingBetween(Integer value1, Integer value2) {
            addCriterion("free_shipping between", value1, value2, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingNotBetween(Integer value1, Integer value2) {
            addCriterion("free_shipping not between", value1, value2, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andValuationIsNull() {
            addCriterion("valuation is null");
            return (Criteria) this;
        }

        public Criteria andValuationIsNotNull() {
            addCriterion("valuation is not null");
            return (Criteria) this;
        }

        public Criteria andValuationEqualTo(String value) {
            addCriterion("valuation =", value, "valuation");
            return (Criteria) this;
        }

        public Criteria andValuationNotEqualTo(String value) {
            addCriterion("valuation <>", value, "valuation");
            return (Criteria) this;
        }

        public Criteria andValuationGreaterThan(String value) {
            addCriterion("valuation >", value, "valuation");
            return (Criteria) this;
        }

        public Criteria andValuationGreaterThanOrEqualTo(String value) {
            addCriterion("valuation >=", value, "valuation");
            return (Criteria) this;
        }

        public Criteria andValuationLessThan(String value) {
            addCriterion("valuation <", value, "valuation");
            return (Criteria) this;
        }

        public Criteria andValuationLessThanOrEqualTo(String value) {
            addCriterion("valuation <=", value, "valuation");
            return (Criteria) this;
        }

        public Criteria andValuationLike(String value) {
            addCriterion("valuation like", value, "valuation");
            return (Criteria) this;
        }

        public Criteria andValuationNotLike(String value) {
            addCriterion("valuation not like", value, "valuation");
            return (Criteria) this;
        }

        public Criteria andValuationIn(List<String> values) {
            addCriterion("valuation in", values, "valuation");
            return (Criteria) this;
        }

        public Criteria andValuationNotIn(List<String> values) {
            addCriterion("valuation not in", values, "valuation");
            return (Criteria) this;
        }

        public Criteria andValuationBetween(String value1, String value2) {
            addCriterion("valuation between", value1, value2, "valuation");
            return (Criteria) this;
        }

        public Criteria andValuationNotBetween(String value1, String value2) {
            addCriterion("valuation not between", value1, value2, "valuation");
            return (Criteria) this;
        }

        public Criteria andMinCountIsNull() {
            addCriterion("min_count is null");
            return (Criteria) this;
        }

        public Criteria andMinCountIsNotNull() {
            addCriterion("min_count is not null");
            return (Criteria) this;
        }

        public Criteria andMinCountEqualTo(Integer value) {
            addCriterion("min_count =", value, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountNotEqualTo(Integer value) {
            addCriterion("min_count <>", value, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountGreaterThan(Integer value) {
            addCriterion("min_count >", value, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_count >=", value, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountLessThan(Integer value) {
            addCriterion("min_count <", value, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountLessThanOrEqualTo(Integer value) {
            addCriterion("min_count <=", value, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountIn(List<Integer> values) {
            addCriterion("min_count in", values, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountNotIn(List<Integer> values) {
            addCriterion("min_count not in", values, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountBetween(Integer value1, Integer value2) {
            addCriterion("min_count between", value1, value2, "minCount");
            return (Criteria) this;
        }

        public Criteria andMinCountNotBetween(Integer value1, Integer value2) {
            addCriterion("min_count not between", value1, value2, "minCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountIsNull() {
            addCriterion("max_count is null");
            return (Criteria) this;
        }

        public Criteria andMaxCountIsNotNull() {
            addCriterion("max_count is not null");
            return (Criteria) this;
        }

        public Criteria andMaxCountEqualTo(Integer value) {
            addCriterion("max_count =", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountNotEqualTo(Integer value) {
            addCriterion("max_count <>", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountGreaterThan(Integer value) {
            addCriterion("max_count >", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_count >=", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountLessThan(Integer value) {
            addCriterion("max_count <", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountLessThanOrEqualTo(Integer value) {
            addCriterion("max_count <=", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountIn(List<Integer> values) {
            addCriterion("max_count in", values, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountNotIn(List<Integer> values) {
            addCriterion("max_count not in", values, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountBetween(Integer value1, Integer value2) {
            addCriterion("max_count between", value1, value2, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountNotBetween(Integer value1, Integer value2) {
            addCriterion("max_count not between", value1, value2, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMinPriceIsNull() {
            addCriterion("min_price is null");
            return (Criteria) this;
        }

        public Criteria andMinPriceIsNotNull() {
            addCriterion("min_price is not null");
            return (Criteria) this;
        }

        public Criteria andMinPriceEqualTo(Double value) {
            addCriterion("min_price =", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceNotEqualTo(Double value) {
            addCriterion("min_price <>", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceGreaterThan(Double value) {
            addCriterion("min_price >", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("min_price >=", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceLessThan(Double value) {
            addCriterion("min_price <", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceLessThanOrEqualTo(Double value) {
            addCriterion("min_price <=", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceIn(List<Double> values) {
            addCriterion("min_price in", values, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceNotIn(List<Double> values) {
            addCriterion("min_price not in", values, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceBetween(Double value1, Double value2) {
            addCriterion("min_price between", value1, value2, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceNotBetween(Double value1, Double value2) {
            addCriterion("min_price not between", value1, value2, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceIsNull() {
            addCriterion("max_price is null");
            return (Criteria) this;
        }

        public Criteria andMaxPriceIsNotNull() {
            addCriterion("max_price is not null");
            return (Criteria) this;
        }

        public Criteria andMaxPriceEqualTo(Double value) {
            addCriterion("max_price =", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceNotEqualTo(Double value) {
            addCriterion("max_price <>", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceGreaterThan(Double value) {
            addCriterion("max_price >", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("max_price >=", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceLessThan(Double value) {
            addCriterion("max_price <", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceLessThanOrEqualTo(Double value) {
            addCriterion("max_price <=", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceIn(List<Double> values) {
            addCriterion("max_price in", values, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceNotIn(List<Double> values) {
            addCriterion("max_price not in", values, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceBetween(Double value1, Double value2) {
            addCriterion("max_price between", value1, value2, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceNotBetween(Double value1, Double value2) {
            addCriterion("max_price not between", value1, value2, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMinWeightIsNull() {
            addCriterion("min_weight is null");
            return (Criteria) this;
        }

        public Criteria andMinWeightIsNotNull() {
            addCriterion("min_weight is not null");
            return (Criteria) this;
        }

        public Criteria andMinWeightEqualTo(Double value) {
            addCriterion("min_weight =", value, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightNotEqualTo(Double value) {
            addCriterion("min_weight <>", value, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightGreaterThan(Double value) {
            addCriterion("min_weight >", value, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightGreaterThanOrEqualTo(Double value) {
            addCriterion("min_weight >=", value, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightLessThan(Double value) {
            addCriterion("min_weight <", value, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightLessThanOrEqualTo(Double value) {
            addCriterion("min_weight <=", value, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightIn(List<Double> values) {
            addCriterion("min_weight in", values, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightNotIn(List<Double> values) {
            addCriterion("min_weight not in", values, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightBetween(Double value1, Double value2) {
            addCriterion("min_weight between", value1, value2, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightNotBetween(Double value1, Double value2) {
            addCriterion("min_weight not between", value1, value2, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightIsNull() {
            addCriterion("max_weight is null");
            return (Criteria) this;
        }

        public Criteria andMaxWeightIsNotNull() {
            addCriterion("max_weight is not null");
            return (Criteria) this;
        }

        public Criteria andMaxWeightEqualTo(Double value) {
            addCriterion("max_weight =", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightNotEqualTo(Double value) {
            addCriterion("max_weight <>", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightGreaterThan(Double value) {
            addCriterion("max_weight >", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightGreaterThanOrEqualTo(Double value) {
            addCriterion("max_weight >=", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightLessThan(Double value) {
            addCriterion("max_weight <", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightLessThanOrEqualTo(Double value) {
            addCriterion("max_weight <=", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightIn(List<Double> values) {
            addCriterion("max_weight in", values, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightNotIn(List<Double> values) {
            addCriterion("max_weight not in", values, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightBetween(Double value1, Double value2) {
            addCriterion("max_weight between", value1, value2, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightNotBetween(Double value1, Double value2) {
            addCriterion("max_weight not between", value1, value2, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andDefaultyIsNull() {
            addCriterion("defaulty is null");
            return (Criteria) this;
        }

        public Criteria andDefaultyIsNotNull() {
            addCriterion("defaulty is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultyEqualTo(Integer value) {
            addCriterion("defaulty =", value, "defaulty");
            return (Criteria) this;
        }

        public Criteria andDefaultyNotEqualTo(Integer value) {
            addCriterion("defaulty <>", value, "defaulty");
            return (Criteria) this;
        }

        public Criteria andDefaultyGreaterThan(Integer value) {
            addCriterion("defaulty >", value, "defaulty");
            return (Criteria) this;
        }

        public Criteria andDefaultyGreaterThanOrEqualTo(Integer value) {
            addCriterion("defaulty >=", value, "defaulty");
            return (Criteria) this;
        }

        public Criteria andDefaultyLessThan(Integer value) {
            addCriterion("defaulty <", value, "defaulty");
            return (Criteria) this;
        }

        public Criteria andDefaultyLessThanOrEqualTo(Integer value) {
            addCriterion("defaulty <=", value, "defaulty");
            return (Criteria) this;
        }

        public Criteria andDefaultyIn(List<Integer> values) {
            addCriterion("defaulty in", values, "defaulty");
            return (Criteria) this;
        }

        public Criteria andDefaultyNotIn(List<Integer> values) {
            addCriterion("defaulty not in", values, "defaulty");
            return (Criteria) this;
        }

        public Criteria andDefaultyBetween(Integer value1, Integer value2) {
            addCriterion("defaulty between", value1, value2, "defaulty");
            return (Criteria) this;
        }

        public Criteria andDefaultyNotBetween(Integer value1, Integer value2) {
            addCriterion("defaulty not between", value1, value2, "defaulty");
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