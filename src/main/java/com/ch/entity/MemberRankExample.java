package com.ch.entity;

import java.util.ArrayList;
import java.util.List;

public class MemberRankExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberRankExample() {
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

        public Criteria andBronzeIsNull() {
            addCriterion("bronze is null");
            return (Criteria) this;
        }

        public Criteria andBronzeIsNotNull() {
            addCriterion("bronze is not null");
            return (Criteria) this;
        }

        public Criteria andBronzeEqualTo(Integer value) {
            addCriterion("bronze =", value, "bronze");
            return (Criteria) this;
        }

        public Criteria andBronzeNotEqualTo(Integer value) {
            addCriterion("bronze <>", value, "bronze");
            return (Criteria) this;
        }

        public Criteria andBronzeGreaterThan(Integer value) {
            addCriterion("bronze >", value, "bronze");
            return (Criteria) this;
        }

        public Criteria andBronzeGreaterThanOrEqualTo(Integer value) {
            addCriterion("bronze >=", value, "bronze");
            return (Criteria) this;
        }

        public Criteria andBronzeLessThan(Integer value) {
            addCriterion("bronze <", value, "bronze");
            return (Criteria) this;
        }

        public Criteria andBronzeLessThanOrEqualTo(Integer value) {
            addCriterion("bronze <=", value, "bronze");
            return (Criteria) this;
        }

        public Criteria andBronzeIn(List<Integer> values) {
            addCriterion("bronze in", values, "bronze");
            return (Criteria) this;
        }

        public Criteria andBronzeNotIn(List<Integer> values) {
            addCriterion("bronze not in", values, "bronze");
            return (Criteria) this;
        }

        public Criteria andBronzeBetween(Integer value1, Integer value2) {
            addCriterion("bronze between", value1, value2, "bronze");
            return (Criteria) this;
        }

        public Criteria andBronzeNotBetween(Integer value1, Integer value2) {
            addCriterion("bronze not between", value1, value2, "bronze");
            return (Criteria) this;
        }

        public Criteria andSilverIsNull() {
            addCriterion("silver is null");
            return (Criteria) this;
        }

        public Criteria andSilverIsNotNull() {
            addCriterion("silver is not null");
            return (Criteria) this;
        }

        public Criteria andSilverEqualTo(Integer value) {
            addCriterion("silver =", value, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverNotEqualTo(Integer value) {
            addCriterion("silver <>", value, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverGreaterThan(Integer value) {
            addCriterion("silver >", value, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverGreaterThanOrEqualTo(Integer value) {
            addCriterion("silver >=", value, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverLessThan(Integer value) {
            addCriterion("silver <", value, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverLessThanOrEqualTo(Integer value) {
            addCriterion("silver <=", value, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverIn(List<Integer> values) {
            addCriterion("silver in", values, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverNotIn(List<Integer> values) {
            addCriterion("silver not in", values, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverBetween(Integer value1, Integer value2) {
            addCriterion("silver between", value1, value2, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverNotBetween(Integer value1, Integer value2) {
            addCriterion("silver not between", value1, value2, "silver");
            return (Criteria) this;
        }

        public Criteria andGoldIsNull() {
            addCriterion("gold is null");
            return (Criteria) this;
        }

        public Criteria andGoldIsNotNull() {
            addCriterion("gold is not null");
            return (Criteria) this;
        }

        public Criteria andGoldEqualTo(Integer value) {
            addCriterion("gold =", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldNotEqualTo(Integer value) {
            addCriterion("gold <>", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldGreaterThan(Integer value) {
            addCriterion("gold >", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldGreaterThanOrEqualTo(Integer value) {
            addCriterion("gold >=", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldLessThan(Integer value) {
            addCriterion("gold <", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldLessThanOrEqualTo(Integer value) {
            addCriterion("gold <=", value, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldIn(List<Integer> values) {
            addCriterion("gold in", values, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldNotIn(List<Integer> values) {
            addCriterion("gold not in", values, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldBetween(Integer value1, Integer value2) {
            addCriterion("gold between", value1, value2, "gold");
            return (Criteria) this;
        }

        public Criteria andGoldNotBetween(Integer value1, Integer value2) {
            addCriterion("gold not between", value1, value2, "gold");
            return (Criteria) this;
        }

        public Criteria andPlatinumIsNull() {
            addCriterion("platinum is null");
            return (Criteria) this;
        }

        public Criteria andPlatinumIsNotNull() {
            addCriterion("platinum is not null");
            return (Criteria) this;
        }

        public Criteria andPlatinumEqualTo(Integer value) {
            addCriterion("platinum =", value, "platinum");
            return (Criteria) this;
        }

        public Criteria andPlatinumNotEqualTo(Integer value) {
            addCriterion("platinum <>", value, "platinum");
            return (Criteria) this;
        }

        public Criteria andPlatinumGreaterThan(Integer value) {
            addCriterion("platinum >", value, "platinum");
            return (Criteria) this;
        }

        public Criteria andPlatinumGreaterThanOrEqualTo(Integer value) {
            addCriterion("platinum >=", value, "platinum");
            return (Criteria) this;
        }

        public Criteria andPlatinumLessThan(Integer value) {
            addCriterion("platinum <", value, "platinum");
            return (Criteria) this;
        }

        public Criteria andPlatinumLessThanOrEqualTo(Integer value) {
            addCriterion("platinum <=", value, "platinum");
            return (Criteria) this;
        }

        public Criteria andPlatinumIn(List<Integer> values) {
            addCriterion("platinum in", values, "platinum");
            return (Criteria) this;
        }

        public Criteria andPlatinumNotIn(List<Integer> values) {
            addCriterion("platinum not in", values, "platinum");
            return (Criteria) this;
        }

        public Criteria andPlatinumBetween(Integer value1, Integer value2) {
            addCriterion("platinum between", value1, value2, "platinum");
            return (Criteria) this;
        }

        public Criteria andPlatinumNotBetween(Integer value1, Integer value2) {
            addCriterion("platinum not between", value1, value2, "platinum");
            return (Criteria) this;
        }

        public Criteria andDiamondsIsNull() {
            addCriterion("diamonds is null");
            return (Criteria) this;
        }

        public Criteria andDiamondsIsNotNull() {
            addCriterion("diamonds is not null");
            return (Criteria) this;
        }

        public Criteria andDiamondsEqualTo(Integer value) {
            addCriterion("diamonds =", value, "diamonds");
            return (Criteria) this;
        }

        public Criteria andDiamondsNotEqualTo(Integer value) {
            addCriterion("diamonds <>", value, "diamonds");
            return (Criteria) this;
        }

        public Criteria andDiamondsGreaterThan(Integer value) {
            addCriterion("diamonds >", value, "diamonds");
            return (Criteria) this;
        }

        public Criteria andDiamondsGreaterThanOrEqualTo(Integer value) {
            addCriterion("diamonds >=", value, "diamonds");
            return (Criteria) this;
        }

        public Criteria andDiamondsLessThan(Integer value) {
            addCriterion("diamonds <", value, "diamonds");
            return (Criteria) this;
        }

        public Criteria andDiamondsLessThanOrEqualTo(Integer value) {
            addCriterion("diamonds <=", value, "diamonds");
            return (Criteria) this;
        }

        public Criteria andDiamondsIn(List<Integer> values) {
            addCriterion("diamonds in", values, "diamonds");
            return (Criteria) this;
        }

        public Criteria andDiamondsNotIn(List<Integer> values) {
            addCriterion("diamonds not in", values, "diamonds");
            return (Criteria) this;
        }

        public Criteria andDiamondsBetween(Integer value1, Integer value2) {
            addCriterion("diamonds between", value1, value2, "diamonds");
            return (Criteria) this;
        }

        public Criteria andDiamondsNotBetween(Integer value1, Integer value2) {
            addCriterion("diamonds not between", value1, value2, "diamonds");
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