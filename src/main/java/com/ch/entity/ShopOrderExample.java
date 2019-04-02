package com.ch.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopOrderExample() {
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

        public Criteria andFreeShippingIsNull() {
            addCriterion("free_shipping is null");
            return (Criteria) this;
        }

        public Criteria andFreeShippingIsNotNull() {
            addCriterion("free_shipping is not null");
            return (Criteria) this;
        }

        public Criteria andFreeShippingEqualTo(Double value) {
            addCriterion("free_shipping =", value, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingNotEqualTo(Double value) {
            addCriterion("free_shipping <>", value, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingGreaterThan(Double value) {
            addCriterion("free_shipping >", value, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingGreaterThanOrEqualTo(Double value) {
            addCriterion("free_shipping >=", value, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingLessThan(Double value) {
            addCriterion("free_shipping <", value, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingLessThanOrEqualTo(Double value) {
            addCriterion("free_shipping <=", value, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingIn(List<Double> values) {
            addCriterion("free_shipping in", values, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingNotIn(List<Double> values) {
            addCriterion("free_shipping not in", values, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingBetween(Double value1, Double value2) {
            addCriterion("free_shipping between", value1, value2, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andFreeShippingNotBetween(Double value1, Double value2) {
            addCriterion("free_shipping not between", value1, value2, "freeShipping");
            return (Criteria) this;
        }

        public Criteria andOrderCloseNormalIsNull() {
            addCriterion("order_close_normal is null");
            return (Criteria) this;
        }

        public Criteria andOrderCloseNormalIsNotNull() {
            addCriterion("order_close_normal is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCloseNormalEqualTo(Integer value) {
            addCriterion("order_close_normal =", value, "orderCloseNormal");
            return (Criteria) this;
        }

        public Criteria andOrderCloseNormalNotEqualTo(Integer value) {
            addCriterion("order_close_normal <>", value, "orderCloseNormal");
            return (Criteria) this;
        }

        public Criteria andOrderCloseNormalGreaterThan(Integer value) {
            addCriterion("order_close_normal >", value, "orderCloseNormal");
            return (Criteria) this;
        }

        public Criteria andOrderCloseNormalGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_close_normal >=", value, "orderCloseNormal");
            return (Criteria) this;
        }

        public Criteria andOrderCloseNormalLessThan(Integer value) {
            addCriterion("order_close_normal <", value, "orderCloseNormal");
            return (Criteria) this;
        }

        public Criteria andOrderCloseNormalLessThanOrEqualTo(Integer value) {
            addCriterion("order_close_normal <=", value, "orderCloseNormal");
            return (Criteria) this;
        }

        public Criteria andOrderCloseNormalIn(List<Integer> values) {
            addCriterion("order_close_normal in", values, "orderCloseNormal");
            return (Criteria) this;
        }

        public Criteria andOrderCloseNormalNotIn(List<Integer> values) {
            addCriterion("order_close_normal not in", values, "orderCloseNormal");
            return (Criteria) this;
        }

        public Criteria andOrderCloseNormalBetween(Integer value1, Integer value2) {
            addCriterion("order_close_normal between", value1, value2, "orderCloseNormal");
            return (Criteria) this;
        }

        public Criteria andOrderCloseNormalNotBetween(Integer value1, Integer value2) {
            addCriterion("order_close_normal not between", value1, value2, "orderCloseNormal");
            return (Criteria) this;
        }

        public Criteria andOrderCloseActiveIsNull() {
            addCriterion("order_close_active is null");
            return (Criteria) this;
        }

        public Criteria andOrderCloseActiveIsNotNull() {
            addCriterion("order_close_active is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCloseActiveEqualTo(Integer value) {
            addCriterion("order_close_active =", value, "orderCloseActive");
            return (Criteria) this;
        }

        public Criteria andOrderCloseActiveNotEqualTo(Integer value) {
            addCriterion("order_close_active <>", value, "orderCloseActive");
            return (Criteria) this;
        }

        public Criteria andOrderCloseActiveGreaterThan(Integer value) {
            addCriterion("order_close_active >", value, "orderCloseActive");
            return (Criteria) this;
        }

        public Criteria andOrderCloseActiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_close_active >=", value, "orderCloseActive");
            return (Criteria) this;
        }

        public Criteria andOrderCloseActiveLessThan(Integer value) {
            addCriterion("order_close_active <", value, "orderCloseActive");
            return (Criteria) this;
        }

        public Criteria andOrderCloseActiveLessThanOrEqualTo(Integer value) {
            addCriterion("order_close_active <=", value, "orderCloseActive");
            return (Criteria) this;
        }

        public Criteria andOrderCloseActiveIn(List<Integer> values) {
            addCriterion("order_close_active in", values, "orderCloseActive");
            return (Criteria) this;
        }

        public Criteria andOrderCloseActiveNotIn(List<Integer> values) {
            addCriterion("order_close_active not in", values, "orderCloseActive");
            return (Criteria) this;
        }

        public Criteria andOrderCloseActiveBetween(Integer value1, Integer value2) {
            addCriterion("order_close_active between", value1, value2, "orderCloseActive");
            return (Criteria) this;
        }

        public Criteria andOrderCloseActiveNotBetween(Integer value1, Integer value2) {
            addCriterion("order_close_active not between", value1, value2, "orderCloseActive");
            return (Criteria) this;
        }

        public Criteria andConfirmDayIsNull() {
            addCriterion("confirm_day is null");
            return (Criteria) this;
        }

        public Criteria andConfirmDayIsNotNull() {
            addCriterion("confirm_day is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmDayEqualTo(Integer value) {
            addCriterion("confirm_day =", value, "confirmDay");
            return (Criteria) this;
        }

        public Criteria andConfirmDayNotEqualTo(Integer value) {
            addCriterion("confirm_day <>", value, "confirmDay");
            return (Criteria) this;
        }

        public Criteria andConfirmDayGreaterThan(Integer value) {
            addCriterion("confirm_day >", value, "confirmDay");
            return (Criteria) this;
        }

        public Criteria andConfirmDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("confirm_day >=", value, "confirmDay");
            return (Criteria) this;
        }

        public Criteria andConfirmDayLessThan(Integer value) {
            addCriterion("confirm_day <", value, "confirmDay");
            return (Criteria) this;
        }

        public Criteria andConfirmDayLessThanOrEqualTo(Integer value) {
            addCriterion("confirm_day <=", value, "confirmDay");
            return (Criteria) this;
        }

        public Criteria andConfirmDayIn(List<Integer> values) {
            addCriterion("confirm_day in", values, "confirmDay");
            return (Criteria) this;
        }

        public Criteria andConfirmDayNotIn(List<Integer> values) {
            addCriterion("confirm_day not in", values, "confirmDay");
            return (Criteria) this;
        }

        public Criteria andConfirmDayBetween(Integer value1, Integer value2) {
            addCriterion("confirm_day between", value1, value2, "confirmDay");
            return (Criteria) this;
        }

        public Criteria andConfirmDayNotBetween(Integer value1, Integer value2) {
            addCriterion("confirm_day not between", value1, value2, "confirmDay");
            return (Criteria) this;
        }

        public Criteria andTemplateCancelIsNull() {
            addCriterion("template_cancel is null");
            return (Criteria) this;
        }

        public Criteria andTemplateCancelIsNotNull() {
            addCriterion("template_cancel is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateCancelEqualTo(String value) {
            addCriterion("template_cancel =", value, "templateCancel");
            return (Criteria) this;
        }

        public Criteria andTemplateCancelNotEqualTo(String value) {
            addCriterion("template_cancel <>", value, "templateCancel");
            return (Criteria) this;
        }

        public Criteria andTemplateCancelGreaterThan(String value) {
            addCriterion("template_cancel >", value, "templateCancel");
            return (Criteria) this;
        }

        public Criteria andTemplateCancelGreaterThanOrEqualTo(String value) {
            addCriterion("template_cancel >=", value, "templateCancel");
            return (Criteria) this;
        }

        public Criteria andTemplateCancelLessThan(String value) {
            addCriterion("template_cancel <", value, "templateCancel");
            return (Criteria) this;
        }

        public Criteria andTemplateCancelLessThanOrEqualTo(String value) {
            addCriterion("template_cancel <=", value, "templateCancel");
            return (Criteria) this;
        }

        public Criteria andTemplateCancelLike(String value) {
            addCriterion("template_cancel like", value, "templateCancel");
            return (Criteria) this;
        }

        public Criteria andTemplateCancelNotLike(String value) {
            addCriterion("template_cancel not like", value, "templateCancel");
            return (Criteria) this;
        }

        public Criteria andTemplateCancelIn(List<String> values) {
            addCriterion("template_cancel in", values, "templateCancel");
            return (Criteria) this;
        }

        public Criteria andTemplateCancelNotIn(List<String> values) {
            addCriterion("template_cancel not in", values, "templateCancel");
            return (Criteria) this;
        }

        public Criteria andTemplateCancelBetween(String value1, String value2) {
            addCriterion("template_cancel between", value1, value2, "templateCancel");
            return (Criteria) this;
        }

        public Criteria andTemplateCancelNotBetween(String value1, String value2) {
            addCriterion("template_cancel not between", value1, value2, "templateCancel");
            return (Criteria) this;
        }

        public Criteria andTemplateDeliveryIsNull() {
            addCriterion("template_delivery is null");
            return (Criteria) this;
        }

        public Criteria andTemplateDeliveryIsNotNull() {
            addCriterion("template_delivery is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateDeliveryEqualTo(String value) {
            addCriterion("template_delivery =", value, "templateDelivery");
            return (Criteria) this;
        }

        public Criteria andTemplateDeliveryNotEqualTo(String value) {
            addCriterion("template_delivery <>", value, "templateDelivery");
            return (Criteria) this;
        }

        public Criteria andTemplateDeliveryGreaterThan(String value) {
            addCriterion("template_delivery >", value, "templateDelivery");
            return (Criteria) this;
        }

        public Criteria andTemplateDeliveryGreaterThanOrEqualTo(String value) {
            addCriterion("template_delivery >=", value, "templateDelivery");
            return (Criteria) this;
        }

        public Criteria andTemplateDeliveryLessThan(String value) {
            addCriterion("template_delivery <", value, "templateDelivery");
            return (Criteria) this;
        }

        public Criteria andTemplateDeliveryLessThanOrEqualTo(String value) {
            addCriterion("template_delivery <=", value, "templateDelivery");
            return (Criteria) this;
        }

        public Criteria andTemplateDeliveryLike(String value) {
            addCriterion("template_delivery like", value, "templateDelivery");
            return (Criteria) this;
        }

        public Criteria andTemplateDeliveryNotLike(String value) {
            addCriterion("template_delivery not like", value, "templateDelivery");
            return (Criteria) this;
        }

        public Criteria andTemplateDeliveryIn(List<String> values) {
            addCriterion("template_delivery in", values, "templateDelivery");
            return (Criteria) this;
        }

        public Criteria andTemplateDeliveryNotIn(List<String> values) {
            addCriterion("template_delivery not in", values, "templateDelivery");
            return (Criteria) this;
        }

        public Criteria andTemplateDeliveryBetween(String value1, String value2) {
            addCriterion("template_delivery between", value1, value2, "templateDelivery");
            return (Criteria) this;
        }

        public Criteria andTemplateDeliveryNotBetween(String value1, String value2) {
            addCriterion("template_delivery not between", value1, value2, "templateDelivery");
            return (Criteria) this;
        }

        public Criteria andTemplateEvaluationIsNull() {
            addCriterion("template_evaluation is null");
            return (Criteria) this;
        }

        public Criteria andTemplateEvaluationIsNotNull() {
            addCriterion("template_evaluation is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateEvaluationEqualTo(String value) {
            addCriterion("template_evaluation =", value, "templateEvaluation");
            return (Criteria) this;
        }

        public Criteria andTemplateEvaluationNotEqualTo(String value) {
            addCriterion("template_evaluation <>", value, "templateEvaluation");
            return (Criteria) this;
        }

        public Criteria andTemplateEvaluationGreaterThan(String value) {
            addCriterion("template_evaluation >", value, "templateEvaluation");
            return (Criteria) this;
        }

        public Criteria andTemplateEvaluationGreaterThanOrEqualTo(String value) {
            addCriterion("template_evaluation >=", value, "templateEvaluation");
            return (Criteria) this;
        }

        public Criteria andTemplateEvaluationLessThan(String value) {
            addCriterion("template_evaluation <", value, "templateEvaluation");
            return (Criteria) this;
        }

        public Criteria andTemplateEvaluationLessThanOrEqualTo(String value) {
            addCriterion("template_evaluation <=", value, "templateEvaluation");
            return (Criteria) this;
        }

        public Criteria andTemplateEvaluationLike(String value) {
            addCriterion("template_evaluation like", value, "templateEvaluation");
            return (Criteria) this;
        }

        public Criteria andTemplateEvaluationNotLike(String value) {
            addCriterion("template_evaluation not like", value, "templateEvaluation");
            return (Criteria) this;
        }

        public Criteria andTemplateEvaluationIn(List<String> values) {
            addCriterion("template_evaluation in", values, "templateEvaluation");
            return (Criteria) this;
        }

        public Criteria andTemplateEvaluationNotIn(List<String> values) {
            addCriterion("template_evaluation not in", values, "templateEvaluation");
            return (Criteria) this;
        }

        public Criteria andTemplateEvaluationBetween(String value1, String value2) {
            addCriterion("template_evaluation between", value1, value2, "templateEvaluation");
            return (Criteria) this;
        }

        public Criteria andTemplateEvaluationNotBetween(String value1, String value2) {
            addCriterion("template_evaluation not between", value1, value2, "templateEvaluation");
            return (Criteria) this;
        }

        public Criteria andTemplateCompleteIsNull() {
            addCriterion("template_complete is null");
            return (Criteria) this;
        }

        public Criteria andTemplateCompleteIsNotNull() {
            addCriterion("template_complete is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateCompleteEqualTo(String value) {
            addCriterion("template_complete =", value, "templateComplete");
            return (Criteria) this;
        }

        public Criteria andTemplateCompleteNotEqualTo(String value) {
            addCriterion("template_complete <>", value, "templateComplete");
            return (Criteria) this;
        }

        public Criteria andTemplateCompleteGreaterThan(String value) {
            addCriterion("template_complete >", value, "templateComplete");
            return (Criteria) this;
        }

        public Criteria andTemplateCompleteGreaterThanOrEqualTo(String value) {
            addCriterion("template_complete >=", value, "templateComplete");
            return (Criteria) this;
        }

        public Criteria andTemplateCompleteLessThan(String value) {
            addCriterion("template_complete <", value, "templateComplete");
            return (Criteria) this;
        }

        public Criteria andTemplateCompleteLessThanOrEqualTo(String value) {
            addCriterion("template_complete <=", value, "templateComplete");
            return (Criteria) this;
        }

        public Criteria andTemplateCompleteLike(String value) {
            addCriterion("template_complete like", value, "templateComplete");
            return (Criteria) this;
        }

        public Criteria andTemplateCompleteNotLike(String value) {
            addCriterion("template_complete not like", value, "templateComplete");
            return (Criteria) this;
        }

        public Criteria andTemplateCompleteIn(List<String> values) {
            addCriterion("template_complete in", values, "templateComplete");
            return (Criteria) this;
        }

        public Criteria andTemplateCompleteNotIn(List<String> values) {
            addCriterion("template_complete not in", values, "templateComplete");
            return (Criteria) this;
        }

        public Criteria andTemplateCompleteBetween(String value1, String value2) {
            addCriterion("template_complete between", value1, value2, "templateComplete");
            return (Criteria) this;
        }

        public Criteria andTemplateCompleteNotBetween(String value1, String value2) {
            addCriterion("template_complete not between", value1, value2, "templateComplete");
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