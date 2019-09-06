package com.ch.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RedPacketExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RedPacketExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andFaceValueIsNull() {
            addCriterion("face_value is null");
            return (Criteria) this;
        }

        public Criteria andFaceValueIsNotNull() {
            addCriterion("face_value is not null");
            return (Criteria) this;
        }

        public Criteria andFaceValueEqualTo(Long value) {
            addCriterion("face_value =", value, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueNotEqualTo(Long value) {
            addCriterion("face_value <>", value, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueGreaterThan(Long value) {
            addCriterion("face_value >", value, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueGreaterThanOrEqualTo(Long value) {
            addCriterion("face_value >=", value, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueLessThan(Long value) {
            addCriterion("face_value <", value, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueLessThanOrEqualTo(Long value) {
            addCriterion("face_value <=", value, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueIn(List<Long> values) {
            addCriterion("face_value in", values, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueNotIn(List<Long> values) {
            addCriterion("face_value not in", values, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueBetween(Long value1, Long value2) {
            addCriterion("face_value between", value1, value2, "faceValue");
            return (Criteria) this;
        }

        public Criteria andFaceValueNotBetween(Long value1, Long value2) {
            addCriterion("face_value not between", value1, value2, "faceValue");
            return (Criteria) this;
        }

        public Criteria andStockIsNull() {
            addCriterion("stock is null");
            return (Criteria) this;
        }

        public Criteria andStockIsNotNull() {
            addCriterion("stock is not null");
            return (Criteria) this;
        }

        public Criteria andStockEqualTo(Long value) {
            addCriterion("stock =", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotEqualTo(Long value) {
            addCriterion("stock <>", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThan(Long value) {
            addCriterion("stock >", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockGreaterThanOrEqualTo(Long value) {
            addCriterion("stock >=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThan(Long value) {
            addCriterion("stock <", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockLessThanOrEqualTo(Long value) {
            addCriterion("stock <=", value, "stock");
            return (Criteria) this;
        }

        public Criteria andStockIn(List<Long> values) {
            addCriterion("stock in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotIn(List<Long> values) {
            addCriterion("stock not in", values, "stock");
            return (Criteria) this;
        }

        public Criteria andStockBetween(Long value1, Long value2) {
            addCriterion("stock between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andStockNotBetween(Long value1, Long value2) {
            addCriterion("stock not between", value1, value2, "stock");
            return (Criteria) this;
        }

        public Criteria andSendBeginDateIsNull() {
            addCriterion("send_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andSendBeginDateIsNotNull() {
            addCriterion("send_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andSendBeginDateEqualTo(Date value) {
            addCriterion("send_begin_date =", value, "sendBeginDate");
            return (Criteria) this;
        }

        public Criteria andSendBeginDateNotEqualTo(Date value) {
            addCriterion("send_begin_date <>", value, "sendBeginDate");
            return (Criteria) this;
        }

        public Criteria andSendBeginDateGreaterThan(Date value) {
            addCriterion("send_begin_date >", value, "sendBeginDate");
            return (Criteria) this;
        }

        public Criteria andSendBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("send_begin_date >=", value, "sendBeginDate");
            return (Criteria) this;
        }

        public Criteria andSendBeginDateLessThan(Date value) {
            addCriterion("send_begin_date <", value, "sendBeginDate");
            return (Criteria) this;
        }

        public Criteria andSendBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("send_begin_date <=", value, "sendBeginDate");
            return (Criteria) this;
        }

        public Criteria andSendBeginDateIn(List<Date> values) {
            addCriterion("send_begin_date in", values, "sendBeginDate");
            return (Criteria) this;
        }

        public Criteria andSendBeginDateNotIn(List<Date> values) {
            addCriterion("send_begin_date not in", values, "sendBeginDate");
            return (Criteria) this;
        }

        public Criteria andSendBeginDateBetween(Date value1, Date value2) {
            addCriterion("send_begin_date between", value1, value2, "sendBeginDate");
            return (Criteria) this;
        }

        public Criteria andSendBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("send_begin_date not between", value1, value2, "sendBeginDate");
            return (Criteria) this;
        }

        public Criteria andSendEndDateIsNull() {
            addCriterion("send_end_date is null");
            return (Criteria) this;
        }

        public Criteria andSendEndDateIsNotNull() {
            addCriterion("send_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andSendEndDateEqualTo(Date value) {
            addCriterion("send_end_date =", value, "sendEndDate");
            return (Criteria) this;
        }

        public Criteria andSendEndDateNotEqualTo(Date value) {
            addCriterion("send_end_date <>", value, "sendEndDate");
            return (Criteria) this;
        }

        public Criteria andSendEndDateGreaterThan(Date value) {
            addCriterion("send_end_date >", value, "sendEndDate");
            return (Criteria) this;
        }

        public Criteria andSendEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("send_end_date >=", value, "sendEndDate");
            return (Criteria) this;
        }

        public Criteria andSendEndDateLessThan(Date value) {
            addCriterion("send_end_date <", value, "sendEndDate");
            return (Criteria) this;
        }

        public Criteria andSendEndDateLessThanOrEqualTo(Date value) {
            addCriterion("send_end_date <=", value, "sendEndDate");
            return (Criteria) this;
        }

        public Criteria andSendEndDateIn(List<Date> values) {
            addCriterion("send_end_date in", values, "sendEndDate");
            return (Criteria) this;
        }

        public Criteria andSendEndDateNotIn(List<Date> values) {
            addCriterion("send_end_date not in", values, "sendEndDate");
            return (Criteria) this;
        }

        public Criteria andSendEndDateBetween(Date value1, Date value2) {
            addCriterion("send_end_date between", value1, value2, "sendEndDate");
            return (Criteria) this;
        }

        public Criteria andSendEndDateNotBetween(Date value1, Date value2) {
            addCriterion("send_end_date not between", value1, value2, "sendEndDate");
            return (Criteria) this;
        }

        public Criteria andGoodsRangeIsNull() {
            addCriterion("goods_range is null");
            return (Criteria) this;
        }

        public Criteria andGoodsRangeIsNotNull() {
            addCriterion("goods_range is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsRangeEqualTo(Integer value) {
            addCriterion("goods_range =", value, "goodsRange");
            return (Criteria) this;
        }

        public Criteria andGoodsRangeNotEqualTo(Integer value) {
            addCriterion("goods_range <>", value, "goodsRange");
            return (Criteria) this;
        }

        public Criteria andGoodsRangeGreaterThan(Integer value) {
            addCriterion("goods_range >", value, "goodsRange");
            return (Criteria) this;
        }

        public Criteria andGoodsRangeGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_range >=", value, "goodsRange");
            return (Criteria) this;
        }

        public Criteria andGoodsRangeLessThan(Integer value) {
            addCriterion("goods_range <", value, "goodsRange");
            return (Criteria) this;
        }

        public Criteria andGoodsRangeLessThanOrEqualTo(Integer value) {
            addCriterion("goods_range <=", value, "goodsRange");
            return (Criteria) this;
        }

        public Criteria andGoodsRangeIn(List<Integer> values) {
            addCriterion("goods_range in", values, "goodsRange");
            return (Criteria) this;
        }

        public Criteria andGoodsRangeNotIn(List<Integer> values) {
            addCriterion("goods_range not in", values, "goodsRange");
            return (Criteria) this;
        }

        public Criteria andGoodsRangeBetween(Integer value1, Integer value2) {
            addCriterion("goods_range between", value1, value2, "goodsRange");
            return (Criteria) this;
        }

        public Criteria andGoodsRangeNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_range not between", value1, value2, "goodsRange");
            return (Criteria) this;
        }

        public Criteria andSuperpositionIsNull() {
            addCriterion("superposition is null");
            return (Criteria) this;
        }

        public Criteria andSuperpositionIsNotNull() {
            addCriterion("superposition is not null");
            return (Criteria) this;
        }

        public Criteria andSuperpositionEqualTo(Integer value) {
            addCriterion("superposition =", value, "superposition");
            return (Criteria) this;
        }

        public Criteria andSuperpositionNotEqualTo(Integer value) {
            addCriterion("superposition <>", value, "superposition");
            return (Criteria) this;
        }

        public Criteria andSuperpositionGreaterThan(Integer value) {
            addCriterion("superposition >", value, "superposition");
            return (Criteria) this;
        }

        public Criteria andSuperpositionGreaterThanOrEqualTo(Integer value) {
            addCriterion("superposition >=", value, "superposition");
            return (Criteria) this;
        }

        public Criteria andSuperpositionLessThan(Integer value) {
            addCriterion("superposition <", value, "superposition");
            return (Criteria) this;
        }

        public Criteria andSuperpositionLessThanOrEqualTo(Integer value) {
            addCriterion("superposition <=", value, "superposition");
            return (Criteria) this;
        }

        public Criteria andSuperpositionIn(List<Integer> values) {
            addCriterion("superposition in", values, "superposition");
            return (Criteria) this;
        }

        public Criteria andSuperpositionNotIn(List<Integer> values) {
            addCriterion("superposition not in", values, "superposition");
            return (Criteria) this;
        }

        public Criteria andSuperpositionBetween(Integer value1, Integer value2) {
            addCriterion("superposition between", value1, value2, "superposition");
            return (Criteria) this;
        }

        public Criteria andSuperpositionNotBetween(Integer value1, Integer value2) {
            addCriterion("superposition not between", value1, value2, "superposition");
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

        public Criteria andMinPriceEqualTo(Long value) {
            addCriterion("min_price =", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceNotEqualTo(Long value) {
            addCriterion("min_price <>", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceGreaterThan(Long value) {
            addCriterion("min_price >", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("min_price >=", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceLessThan(Long value) {
            addCriterion("min_price <", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceLessThanOrEqualTo(Long value) {
            addCriterion("min_price <=", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceIn(List<Long> values) {
            addCriterion("min_price in", values, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceNotIn(List<Long> values) {
            addCriterion("min_price not in", values, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceBetween(Long value1, Long value2) {
            addCriterion("min_price between", value1, value2, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceNotBetween(Long value1, Long value2) {
            addCriterion("min_price not between", value1, value2, "minPrice");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateIsNull() {
            addCriterion("use_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateIsNotNull() {
            addCriterion("use_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateEqualTo(Date value) {
            addCriterion("use_begin_date =", value, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateNotEqualTo(Date value) {
            addCriterion("use_begin_date <>", value, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateGreaterThan(Date value) {
            addCriterion("use_begin_date >", value, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("use_begin_date >=", value, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateLessThan(Date value) {
            addCriterion("use_begin_date <", value, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("use_begin_date <=", value, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateIn(List<Date> values) {
            addCriterion("use_begin_date in", values, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateNotIn(List<Date> values) {
            addCriterion("use_begin_date not in", values, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateBetween(Date value1, Date value2) {
            addCriterion("use_begin_date between", value1, value2, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("use_begin_date not between", value1, value2, "useBeginDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateIsNull() {
            addCriterion("use_end_date is null");
            return (Criteria) this;
        }

        public Criteria andUseEndDateIsNotNull() {
            addCriterion("use_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andUseEndDateEqualTo(Date value) {
            addCriterion("use_end_date =", value, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateNotEqualTo(Date value) {
            addCriterion("use_end_date <>", value, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateGreaterThan(Date value) {
            addCriterion("use_end_date >", value, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("use_end_date >=", value, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateLessThan(Date value) {
            addCriterion("use_end_date <", value, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateLessThanOrEqualTo(Date value) {
            addCriterion("use_end_date <=", value, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateIn(List<Date> values) {
            addCriterion("use_end_date in", values, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateNotIn(List<Date> values) {
            addCriterion("use_end_date not in", values, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateBetween(Date value1, Date value2) {
            addCriterion("use_end_date between", value1, value2, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andUseEndDateNotBetween(Date value1, Date value2) {
            addCriterion("use_end_date not between", value1, value2, "useEndDate");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeIsNull() {
            addCriterion("red_packet_type is null");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeIsNotNull() {
            addCriterion("red_packet_type is not null");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeEqualTo(String value) {
            addCriterion("red_packet_type =", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeNotEqualTo(String value) {
            addCriterion("red_packet_type <>", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeGreaterThan(String value) {
            addCriterion("red_packet_type >", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeGreaterThanOrEqualTo(String value) {
            addCriterion("red_packet_type >=", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeLessThan(String value) {
            addCriterion("red_packet_type <", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeLessThanOrEqualTo(String value) {
            addCriterion("red_packet_type <=", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeLike(String value) {
            addCriterion("red_packet_type like", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeNotLike(String value) {
            addCriterion("red_packet_type not like", value, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeIn(List<String> values) {
            addCriterion("red_packet_type in", values, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeNotIn(List<String> values) {
            addCriterion("red_packet_type not in", values, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeBetween(String value1, String value2) {
            addCriterion("red_packet_type between", value1, value2, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andRedPacketTypeNotBetween(String value1, String value2) {
            addCriterion("red_packet_type not between", value1, value2, "redPacketType");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Long value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Long value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Long value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Long value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Long value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Long value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Long> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Long> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Long value1, Long value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Long value1, Long value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateIsNull() {
            addCriterion("modify_date is null");
            return (Criteria) this;
        }

        public Criteria andModifyDateIsNotNull() {
            addCriterion("modify_date is not null");
            return (Criteria) this;
        }

        public Criteria andModifyDateEqualTo(Date value) {
            addCriterion("modify_date =", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotEqualTo(Date value) {
            addCriterion("modify_date <>", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateGreaterThan(Date value) {
            addCriterion("modify_date >", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_date >=", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateLessThan(Date value) {
            addCriterion("modify_date <", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateLessThanOrEqualTo(Date value) {
            addCriterion("modify_date <=", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateIn(List<Date> values) {
            addCriterion("modify_date in", values, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotIn(List<Date> values) {
            addCriterion("modify_date not in", values, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateBetween(Date value1, Date value2) {
            addCriterion("modify_date between", value1, value2, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotBetween(Date value1, Date value2) {
            addCriterion("modify_date not between", value1, value2, "modifyDate");
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