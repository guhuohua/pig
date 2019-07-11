package com.ch.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LookShopExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LookShopExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andClientIdIsNull() {
            addCriterion("client_id is null");
            return (Criteria) this;
        }

        public Criteria andClientIdIsNotNull() {
            addCriterion("client_id is not null");
            return (Criteria) this;
        }

        public Criteria andClientIdEqualTo(Integer value) {
            addCriterion("client_id =", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotEqualTo(Integer value) {
            addCriterion("client_id <>", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThan(Integer value) {
            addCriterion("client_id >", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("client_id >=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThan(Integer value) {
            addCriterion("client_id <", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThanOrEqualTo(Integer value) {
            addCriterion("client_id <=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdIn(List<Integer> values) {
            addCriterion("client_id in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotIn(List<Integer> values) {
            addCriterion("client_id not in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdBetween(Integer value1, Integer value2) {
            addCriterion("client_id between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotBetween(Integer value1, Integer value2) {
            addCriterion("client_id not between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdIsNull() {
            addCriterion("business_type_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdIsNotNull() {
            addCriterion("business_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdEqualTo(Long value) {
            addCriterion("business_type_id =", value, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdNotEqualTo(Long value) {
            addCriterion("business_type_id <>", value, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdGreaterThan(Long value) {
            addCriterion("business_type_id >", value, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("business_type_id >=", value, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdLessThan(Long value) {
            addCriterion("business_type_id <", value, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("business_type_id <=", value, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdIn(List<Long> values) {
            addCriterion("business_type_id in", values, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdNotIn(List<Long> values) {
            addCriterion("business_type_id not in", values, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdBetween(Long value1, Long value2) {
            addCriterion("business_type_id between", value1, value2, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("business_type_id not between", value1, value2, "businessTypeId");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIdIsNull() {
            addCriterion("property_type_id is null");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIdIsNotNull() {
            addCriterion("property_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIdEqualTo(Long value) {
            addCriterion("property_type_id =", value, "propertyTypeId");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIdNotEqualTo(Long value) {
            addCriterion("property_type_id <>", value, "propertyTypeId");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIdGreaterThan(Long value) {
            addCriterion("property_type_id >", value, "propertyTypeId");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("property_type_id >=", value, "propertyTypeId");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIdLessThan(Long value) {
            addCriterion("property_type_id <", value, "propertyTypeId");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("property_type_id <=", value, "propertyTypeId");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIdIn(List<Long> values) {
            addCriterion("property_type_id in", values, "propertyTypeId");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIdNotIn(List<Long> values) {
            addCriterion("property_type_id not in", values, "propertyTypeId");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIdBetween(Long value1, Long value2) {
            addCriterion("property_type_id between", value1, value2, "propertyTypeId");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("property_type_id not between", value1, value2, "propertyTypeId");
            return (Criteria) this;
        }

        public Criteria andShopTypeIdIsNull() {
            addCriterion("shop_type_id is null");
            return (Criteria) this;
        }

        public Criteria andShopTypeIdIsNotNull() {
            addCriterion("shop_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andShopTypeIdEqualTo(Long value) {
            addCriterion("shop_type_id =", value, "shopTypeId");
            return (Criteria) this;
        }

        public Criteria andShopTypeIdNotEqualTo(Long value) {
            addCriterion("shop_type_id <>", value, "shopTypeId");
            return (Criteria) this;
        }

        public Criteria andShopTypeIdGreaterThan(Long value) {
            addCriterion("shop_type_id >", value, "shopTypeId");
            return (Criteria) this;
        }

        public Criteria andShopTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("shop_type_id >=", value, "shopTypeId");
            return (Criteria) this;
        }

        public Criteria andShopTypeIdLessThan(Long value) {
            addCriterion("shop_type_id <", value, "shopTypeId");
            return (Criteria) this;
        }

        public Criteria andShopTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("shop_type_id <=", value, "shopTypeId");
            return (Criteria) this;
        }

        public Criteria andShopTypeIdIn(List<Long> values) {
            addCriterion("shop_type_id in", values, "shopTypeId");
            return (Criteria) this;
        }

        public Criteria andShopTypeIdNotIn(List<Long> values) {
            addCriterion("shop_type_id not in", values, "shopTypeId");
            return (Criteria) this;
        }

        public Criteria andShopTypeIdBetween(Long value1, Long value2) {
            addCriterion("shop_type_id between", value1, value2, "shopTypeId");
            return (Criteria) this;
        }

        public Criteria andShopTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("shop_type_id not between", value1, value2, "shopTypeId");
            return (Criteria) this;
        }

        public Criteria andRentRangeIdIsNull() {
            addCriterion("rent_range_id is null");
            return (Criteria) this;
        }

        public Criteria andRentRangeIdIsNotNull() {
            addCriterion("rent_range_id is not null");
            return (Criteria) this;
        }

        public Criteria andRentRangeIdEqualTo(Long value) {
            addCriterion("rent_range_id =", value, "rentRangeId");
            return (Criteria) this;
        }

        public Criteria andRentRangeIdNotEqualTo(Long value) {
            addCriterion("rent_range_id <>", value, "rentRangeId");
            return (Criteria) this;
        }

        public Criteria andRentRangeIdGreaterThan(Long value) {
            addCriterion("rent_range_id >", value, "rentRangeId");
            return (Criteria) this;
        }

        public Criteria andRentRangeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rent_range_id >=", value, "rentRangeId");
            return (Criteria) this;
        }

        public Criteria andRentRangeIdLessThan(Long value) {
            addCriterion("rent_range_id <", value, "rentRangeId");
            return (Criteria) this;
        }

        public Criteria andRentRangeIdLessThanOrEqualTo(Long value) {
            addCriterion("rent_range_id <=", value, "rentRangeId");
            return (Criteria) this;
        }

        public Criteria andRentRangeIdIn(List<Long> values) {
            addCriterion("rent_range_id in", values, "rentRangeId");
            return (Criteria) this;
        }

        public Criteria andRentRangeIdNotIn(List<Long> values) {
            addCriterion("rent_range_id not in", values, "rentRangeId");
            return (Criteria) this;
        }

        public Criteria andRentRangeIdBetween(Long value1, Long value2) {
            addCriterion("rent_range_id between", value1, value2, "rentRangeId");
            return (Criteria) this;
        }

        public Criteria andRentRangeIdNotBetween(Long value1, Long value2) {
            addCriterion("rent_range_id not between", value1, value2, "rentRangeId");
            return (Criteria) this;
        }

        public Criteria andSmallRentIsNull() {
            addCriterion("small_rent is null");
            return (Criteria) this;
        }

        public Criteria andSmallRentIsNotNull() {
            addCriterion("small_rent is not null");
            return (Criteria) this;
        }

        public Criteria andSmallRentEqualTo(Long value) {
            addCriterion("small_rent =", value, "smallRent");
            return (Criteria) this;
        }

        public Criteria andSmallRentNotEqualTo(Long value) {
            addCriterion("small_rent <>", value, "smallRent");
            return (Criteria) this;
        }

        public Criteria andSmallRentGreaterThan(Long value) {
            addCriterion("small_rent >", value, "smallRent");
            return (Criteria) this;
        }

        public Criteria andSmallRentGreaterThanOrEqualTo(Long value) {
            addCriterion("small_rent >=", value, "smallRent");
            return (Criteria) this;
        }

        public Criteria andSmallRentLessThan(Long value) {
            addCriterion("small_rent <", value, "smallRent");
            return (Criteria) this;
        }

        public Criteria andSmallRentLessThanOrEqualTo(Long value) {
            addCriterion("small_rent <=", value, "smallRent");
            return (Criteria) this;
        }

        public Criteria andSmallRentIn(List<Long> values) {
            addCriterion("small_rent in", values, "smallRent");
            return (Criteria) this;
        }

        public Criteria andSmallRentNotIn(List<Long> values) {
            addCriterion("small_rent not in", values, "smallRent");
            return (Criteria) this;
        }

        public Criteria andSmallRentBetween(Long value1, Long value2) {
            addCriterion("small_rent between", value1, value2, "smallRent");
            return (Criteria) this;
        }

        public Criteria andSmallRentNotBetween(Long value1, Long value2) {
            addCriterion("small_rent not between", value1, value2, "smallRent");
            return (Criteria) this;
        }

        public Criteria andTopRentIsNull() {
            addCriterion("top_rent is null");
            return (Criteria) this;
        }

        public Criteria andTopRentIsNotNull() {
            addCriterion("top_rent is not null");
            return (Criteria) this;
        }

        public Criteria andTopRentEqualTo(Long value) {
            addCriterion("top_rent =", value, "topRent");
            return (Criteria) this;
        }

        public Criteria andTopRentNotEqualTo(Long value) {
            addCriterion("top_rent <>", value, "topRent");
            return (Criteria) this;
        }

        public Criteria andTopRentGreaterThan(Long value) {
            addCriterion("top_rent >", value, "topRent");
            return (Criteria) this;
        }

        public Criteria andTopRentGreaterThanOrEqualTo(Long value) {
            addCriterion("top_rent >=", value, "topRent");
            return (Criteria) this;
        }

        public Criteria andTopRentLessThan(Long value) {
            addCriterion("top_rent <", value, "topRent");
            return (Criteria) this;
        }

        public Criteria andTopRentLessThanOrEqualTo(Long value) {
            addCriterion("top_rent <=", value, "topRent");
            return (Criteria) this;
        }

        public Criteria andTopRentIn(List<Long> values) {
            addCriterion("top_rent in", values, "topRent");
            return (Criteria) this;
        }

        public Criteria andTopRentNotIn(List<Long> values) {
            addCriterion("top_rent not in", values, "topRent");
            return (Criteria) this;
        }

        public Criteria andTopRentBetween(Long value1, Long value2) {
            addCriterion("top_rent between", value1, value2, "topRent");
            return (Criteria) this;
        }

        public Criteria andTopRentNotBetween(Long value1, Long value2) {
            addCriterion("top_rent not between", value1, value2, "topRent");
            return (Criteria) this;
        }

        public Criteria andSmallAreaIsNull() {
            addCriterion("small_area is null");
            return (Criteria) this;
        }

        public Criteria andSmallAreaIsNotNull() {
            addCriterion("small_area is not null");
            return (Criteria) this;
        }

        public Criteria andSmallAreaEqualTo(Long value) {
            addCriterion("small_area =", value, "smallArea");
            return (Criteria) this;
        }

        public Criteria andSmallAreaNotEqualTo(Long value) {
            addCriterion("small_area <>", value, "smallArea");
            return (Criteria) this;
        }

        public Criteria andSmallAreaGreaterThan(Long value) {
            addCriterion("small_area >", value, "smallArea");
            return (Criteria) this;
        }

        public Criteria andSmallAreaGreaterThanOrEqualTo(Long value) {
            addCriterion("small_area >=", value, "smallArea");
            return (Criteria) this;
        }

        public Criteria andSmallAreaLessThan(Long value) {
            addCriterion("small_area <", value, "smallArea");
            return (Criteria) this;
        }

        public Criteria andSmallAreaLessThanOrEqualTo(Long value) {
            addCriterion("small_area <=", value, "smallArea");
            return (Criteria) this;
        }

        public Criteria andSmallAreaIn(List<Long> values) {
            addCriterion("small_area in", values, "smallArea");
            return (Criteria) this;
        }

        public Criteria andSmallAreaNotIn(List<Long> values) {
            addCriterion("small_area not in", values, "smallArea");
            return (Criteria) this;
        }

        public Criteria andSmallAreaBetween(Long value1, Long value2) {
            addCriterion("small_area between", value1, value2, "smallArea");
            return (Criteria) this;
        }

        public Criteria andSmallAreaNotBetween(Long value1, Long value2) {
            addCriterion("small_area not between", value1, value2, "smallArea");
            return (Criteria) this;
        }

        public Criteria andTopAreaIsNull() {
            addCriterion("top_area is null");
            return (Criteria) this;
        }

        public Criteria andTopAreaIsNotNull() {
            addCriterion("top_area is not null");
            return (Criteria) this;
        }

        public Criteria andTopAreaEqualTo(Long value) {
            addCriterion("top_area =", value, "topArea");
            return (Criteria) this;
        }

        public Criteria andTopAreaNotEqualTo(Long value) {
            addCriterion("top_area <>", value, "topArea");
            return (Criteria) this;
        }

        public Criteria andTopAreaGreaterThan(Long value) {
            addCriterion("top_area >", value, "topArea");
            return (Criteria) this;
        }

        public Criteria andTopAreaGreaterThanOrEqualTo(Long value) {
            addCriterion("top_area >=", value, "topArea");
            return (Criteria) this;
        }

        public Criteria andTopAreaLessThan(Long value) {
            addCriterion("top_area <", value, "topArea");
            return (Criteria) this;
        }

        public Criteria andTopAreaLessThanOrEqualTo(Long value) {
            addCriterion("top_area <=", value, "topArea");
            return (Criteria) this;
        }

        public Criteria andTopAreaIn(List<Long> values) {
            addCriterion("top_area in", values, "topArea");
            return (Criteria) this;
        }

        public Criteria andTopAreaNotIn(List<Long> values) {
            addCriterion("top_area not in", values, "topArea");
            return (Criteria) this;
        }

        public Criteria andTopAreaBetween(Long value1, Long value2) {
            addCriterion("top_area between", value1, value2, "topArea");
            return (Criteria) this;
        }

        public Criteria andTopAreaNotBetween(Long value1, Long value2) {
            addCriterion("top_area not between", value1, value2, "topArea");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIsNull() {
            addCriterion("transfer_status is null");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIsNotNull() {
            addCriterion("transfer_status is not null");
            return (Criteria) this;
        }

        public Criteria andTransferStatusEqualTo(Integer value) {
            addCriterion("transfer_status =", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotEqualTo(Integer value) {
            addCriterion("transfer_status <>", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusGreaterThan(Integer value) {
            addCriterion("transfer_status >", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("transfer_status >=", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusLessThan(Integer value) {
            addCriterion("transfer_status <", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusLessThanOrEqualTo(Integer value) {
            addCriterion("transfer_status <=", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIn(List<Integer> values) {
            addCriterion("transfer_status in", values, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotIn(List<Integer> values) {
            addCriterion("transfer_status not in", values, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusBetween(Integer value1, Integer value2) {
            addCriterion("transfer_status between", value1, value2, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("transfer_status not between", value1, value2, "transferStatus");
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("city_id is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("city_id is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(String value) {
            addCriterion("city_id =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(String value) {
            addCriterion("city_id <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(String value) {
            addCriterion("city_id >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("city_id >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(String value) {
            addCriterion("city_id <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(String value) {
            addCriterion("city_id <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLike(String value) {
            addCriterion("city_id like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotLike(String value) {
            addCriterion("city_id not like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<String> values) {
            addCriterion("city_id in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<String> values) {
            addCriterion("city_id not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(String value1, String value2) {
            addCriterion("city_id between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(String value1, String value2) {
            addCriterion("city_id not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNull() {
            addCriterion("area_id is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("area_id is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(String value) {
            addCriterion("area_id =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(String value) {
            addCriterion("area_id <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(String value) {
            addCriterion("area_id >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("area_id >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(String value) {
            addCriterion("area_id <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(String value) {
            addCriterion("area_id <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLike(String value) {
            addCriterion("area_id like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotLike(String value) {
            addCriterion("area_id not like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<String> values) {
            addCriterion("area_id in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<String> values) {
            addCriterion("area_id not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(String value1, String value2) {
            addCriterion("area_id between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(String value1, String value2) {
            addCriterion("area_id not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andDecorateTypeIdIsNull() {
            addCriterion("decorate_type_id is null");
            return (Criteria) this;
        }

        public Criteria andDecorateTypeIdIsNotNull() {
            addCriterion("decorate_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andDecorateTypeIdEqualTo(Long value) {
            addCriterion("decorate_type_id =", value, "decorateTypeId");
            return (Criteria) this;
        }

        public Criteria andDecorateTypeIdNotEqualTo(Long value) {
            addCriterion("decorate_type_id <>", value, "decorateTypeId");
            return (Criteria) this;
        }

        public Criteria andDecorateTypeIdGreaterThan(Long value) {
            addCriterion("decorate_type_id >", value, "decorateTypeId");
            return (Criteria) this;
        }

        public Criteria andDecorateTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("decorate_type_id >=", value, "decorateTypeId");
            return (Criteria) this;
        }

        public Criteria andDecorateTypeIdLessThan(Long value) {
            addCriterion("decorate_type_id <", value, "decorateTypeId");
            return (Criteria) this;
        }

        public Criteria andDecorateTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("decorate_type_id <=", value, "decorateTypeId");
            return (Criteria) this;
        }

        public Criteria andDecorateTypeIdIn(List<Long> values) {
            addCriterion("decorate_type_id in", values, "decorateTypeId");
            return (Criteria) this;
        }

        public Criteria andDecorateTypeIdNotIn(List<Long> values) {
            addCriterion("decorate_type_id not in", values, "decorateTypeId");
            return (Criteria) this;
        }

        public Criteria andDecorateTypeIdBetween(Long value1, Long value2) {
            addCriterion("decorate_type_id between", value1, value2, "decorateTypeId");
            return (Criteria) this;
        }

        public Criteria andDecorateTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("decorate_type_id not between", value1, value2, "decorateTypeId");
            return (Criteria) this;
        }

        public Criteria andRequirementDetailsIsNull() {
            addCriterion("requirement_details is null");
            return (Criteria) this;
        }

        public Criteria andRequirementDetailsIsNotNull() {
            addCriterion("requirement_details is not null");
            return (Criteria) this;
        }

        public Criteria andRequirementDetailsEqualTo(String value) {
            addCriterion("requirement_details =", value, "requirementDetails");
            return (Criteria) this;
        }

        public Criteria andRequirementDetailsNotEqualTo(String value) {
            addCriterion("requirement_details <>", value, "requirementDetails");
            return (Criteria) this;
        }

        public Criteria andRequirementDetailsGreaterThan(String value) {
            addCriterion("requirement_details >", value, "requirementDetails");
            return (Criteria) this;
        }

        public Criteria andRequirementDetailsGreaterThanOrEqualTo(String value) {
            addCriterion("requirement_details >=", value, "requirementDetails");
            return (Criteria) this;
        }

        public Criteria andRequirementDetailsLessThan(String value) {
            addCriterion("requirement_details <", value, "requirementDetails");
            return (Criteria) this;
        }

        public Criteria andRequirementDetailsLessThanOrEqualTo(String value) {
            addCriterion("requirement_details <=", value, "requirementDetails");
            return (Criteria) this;
        }

        public Criteria andRequirementDetailsLike(String value) {
            addCriterion("requirement_details like", value, "requirementDetails");
            return (Criteria) this;
        }

        public Criteria andRequirementDetailsNotLike(String value) {
            addCriterion("requirement_details not like", value, "requirementDetails");
            return (Criteria) this;
        }

        public Criteria andRequirementDetailsIn(List<String> values) {
            addCriterion("requirement_details in", values, "requirementDetails");
            return (Criteria) this;
        }

        public Criteria andRequirementDetailsNotIn(List<String> values) {
            addCriterion("requirement_details not in", values, "requirementDetails");
            return (Criteria) this;
        }

        public Criteria andRequirementDetailsBetween(String value1, String value2) {
            addCriterion("requirement_details between", value1, value2, "requirementDetails");
            return (Criteria) this;
        }

        public Criteria andRequirementDetailsNotBetween(String value1, String value2) {
            addCriterion("requirement_details not between", value1, value2, "requirementDetails");
            return (Criteria) this;
        }

        public Criteria andAccessoryRequirementsIsNull() {
            addCriterion("\"accessory _requirements\" is null");
            return (Criteria) this;
        }

        public Criteria andAccessoryRequirementsIsNotNull() {
            addCriterion("\"accessory _requirements\" is not null");
            return (Criteria) this;
        }

        public Criteria andAccessoryRequirementsEqualTo(String value) {
            addCriterion("\"accessory _requirements\" =", value, "accessoryRequirements");
            return (Criteria) this;
        }

        public Criteria andAccessoryRequirementsNotEqualTo(String value) {
            addCriterion("\"accessory _requirements\" <>", value, "accessoryRequirements");
            return (Criteria) this;
        }

        public Criteria andAccessoryRequirementsGreaterThan(String value) {
            addCriterion("\"accessory _requirements\" >", value, "accessoryRequirements");
            return (Criteria) this;
        }

        public Criteria andAccessoryRequirementsGreaterThanOrEqualTo(String value) {
            addCriterion("\"accessory _requirements\" >=", value, "accessoryRequirements");
            return (Criteria) this;
        }

        public Criteria andAccessoryRequirementsLessThan(String value) {
            addCriterion("\"accessory _requirements\" <", value, "accessoryRequirements");
            return (Criteria) this;
        }

        public Criteria andAccessoryRequirementsLessThanOrEqualTo(String value) {
            addCriterion("\"accessory _requirements\" <=", value, "accessoryRequirements");
            return (Criteria) this;
        }

        public Criteria andAccessoryRequirementsLike(String value) {
            addCriterion("\"accessory _requirements\" like", value, "accessoryRequirements");
            return (Criteria) this;
        }

        public Criteria andAccessoryRequirementsNotLike(String value) {
            addCriterion("\"accessory _requirements\" not like", value, "accessoryRequirements");
            return (Criteria) this;
        }

        public Criteria andAccessoryRequirementsIn(List<String> values) {
            addCriterion("\"accessory _requirements\" in", values, "accessoryRequirements");
            return (Criteria) this;
        }

        public Criteria andAccessoryRequirementsNotIn(List<String> values) {
            addCriterion("\"accessory _requirements\" not in", values, "accessoryRequirements");
            return (Criteria) this;
        }

        public Criteria andAccessoryRequirementsBetween(String value1, String value2) {
            addCriterion("\"accessory _requirements\" between", value1, value2, "accessoryRequirements");
            return (Criteria) this;
        }

        public Criteria andAccessoryRequirementsNotBetween(String value1, String value2) {
            addCriterion("\"accessory _requirements\" not between", value1, value2, "accessoryRequirements");
            return (Criteria) this;
        }

        public Criteria andGateWidthIsNull() {
            addCriterion("gate_width is null");
            return (Criteria) this;
        }

        public Criteria andGateWidthIsNotNull() {
            addCriterion("gate_width is not null");
            return (Criteria) this;
        }

        public Criteria andGateWidthEqualTo(Long value) {
            addCriterion("gate_width =", value, "gateWidth");
            return (Criteria) this;
        }

        public Criteria andGateWidthNotEqualTo(Long value) {
            addCriterion("gate_width <>", value, "gateWidth");
            return (Criteria) this;
        }

        public Criteria andGateWidthGreaterThan(Long value) {
            addCriterion("gate_width >", value, "gateWidth");
            return (Criteria) this;
        }

        public Criteria andGateWidthGreaterThanOrEqualTo(Long value) {
            addCriterion("gate_width >=", value, "gateWidth");
            return (Criteria) this;
        }

        public Criteria andGateWidthLessThan(Long value) {
            addCriterion("gate_width <", value, "gateWidth");
            return (Criteria) this;
        }

        public Criteria andGateWidthLessThanOrEqualTo(Long value) {
            addCriterion("gate_width <=", value, "gateWidth");
            return (Criteria) this;
        }

        public Criteria andGateWidthIn(List<Long> values) {
            addCriterion("gate_width in", values, "gateWidth");
            return (Criteria) this;
        }

        public Criteria andGateWidthNotIn(List<Long> values) {
            addCriterion("gate_width not in", values, "gateWidth");
            return (Criteria) this;
        }

        public Criteria andGateWidthBetween(Long value1, Long value2) {
            addCriterion("gate_width between", value1, value2, "gateWidth");
            return (Criteria) this;
        }

        public Criteria andGateWidthNotBetween(Long value1, Long value2) {
            addCriterion("gate_width not between", value1, value2, "gateWidth");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIsNull() {
            addCriterion("service_type is null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIsNotNull() {
            addCriterion("service_type is not null");
            return (Criteria) this;
        }

        public Criteria andServiceTypeEqualTo(Long value) {
            addCriterion("service_type =", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotEqualTo(Long value) {
            addCriterion("service_type <>", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThan(Long value) {
            addCriterion("service_type >", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("service_type >=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThan(Long value) {
            addCriterion("service_type <", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeLessThanOrEqualTo(Long value) {
            addCriterion("service_type <=", value, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeIn(List<Long> values) {
            addCriterion("service_type in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotIn(List<Long> values) {
            addCriterion("service_type not in", values, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeBetween(Long value1, Long value2) {
            addCriterion("service_type between", value1, value2, "serviceType");
            return (Criteria) this;
        }

        public Criteria andServiceTypeNotBetween(Long value1, Long value2) {
            addCriterion("service_type not between", value1, value2, "serviceType");
            return (Criteria) this;
        }

        public Criteria andLngIsNull() {
            addCriterion("lng is null");
            return (Criteria) this;
        }

        public Criteria andLngIsNotNull() {
            addCriterion("lng is not null");
            return (Criteria) this;
        }

        public Criteria andLngEqualTo(String value) {
            addCriterion("lng =", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotEqualTo(String value) {
            addCriterion("lng <>", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThan(String value) {
            addCriterion("lng >", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngGreaterThanOrEqualTo(String value) {
            addCriterion("lng >=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThan(String value) {
            addCriterion("lng <", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLessThanOrEqualTo(String value) {
            addCriterion("lng <=", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngLike(String value) {
            addCriterion("lng like", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotLike(String value) {
            addCriterion("lng not like", value, "lng");
            return (Criteria) this;
        }

        public Criteria andLngIn(List<String> values) {
            addCriterion("lng in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotIn(List<String> values) {
            addCriterion("lng not in", values, "lng");
            return (Criteria) this;
        }

        public Criteria andLngBetween(String value1, String value2) {
            addCriterion("lng between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLngNotBetween(String value1, String value2) {
            addCriterion("lng not between", value1, value2, "lng");
            return (Criteria) this;
        }

        public Criteria andLatIsNull() {
            addCriterion("lat is null");
            return (Criteria) this;
        }

        public Criteria andLatIsNotNull() {
            addCriterion("lat is not null");
            return (Criteria) this;
        }

        public Criteria andLatEqualTo(String value) {
            addCriterion("lat =", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotEqualTo(String value) {
            addCriterion("lat <>", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThan(String value) {
            addCriterion("lat >", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThanOrEqualTo(String value) {
            addCriterion("lat >=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThan(String value) {
            addCriterion("lat <", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThanOrEqualTo(String value) {
            addCriterion("lat <=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLike(String value) {
            addCriterion("lat like", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotLike(String value) {
            addCriterion("lat not like", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatIn(List<String> values) {
            addCriterion("lat in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotIn(List<String> values) {
            addCriterion("lat not in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatBetween(String value1, String value2) {
            addCriterion("lat between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotBetween(String value1, String value2) {
            addCriterion("lat not between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andMediumStatusIsNull() {
            addCriterion("medium_status is null");
            return (Criteria) this;
        }

        public Criteria andMediumStatusIsNotNull() {
            addCriterion("medium_status is not null");
            return (Criteria) this;
        }

        public Criteria andMediumStatusEqualTo(Integer value) {
            addCriterion("medium_status =", value, "mediumStatus");
            return (Criteria) this;
        }

        public Criteria andMediumStatusNotEqualTo(Integer value) {
            addCriterion("medium_status <>", value, "mediumStatus");
            return (Criteria) this;
        }

        public Criteria andMediumStatusGreaterThan(Integer value) {
            addCriterion("medium_status >", value, "mediumStatus");
            return (Criteria) this;
        }

        public Criteria andMediumStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("medium_status >=", value, "mediumStatus");
            return (Criteria) this;
        }

        public Criteria andMediumStatusLessThan(Integer value) {
            addCriterion("medium_status <", value, "mediumStatus");
            return (Criteria) this;
        }

        public Criteria andMediumStatusLessThanOrEqualTo(Integer value) {
            addCriterion("medium_status <=", value, "mediumStatus");
            return (Criteria) this;
        }

        public Criteria andMediumStatusIn(List<Integer> values) {
            addCriterion("medium_status in", values, "mediumStatus");
            return (Criteria) this;
        }

        public Criteria andMediumStatusNotIn(List<Integer> values) {
            addCriterion("medium_status not in", values, "mediumStatus");
            return (Criteria) this;
        }

        public Criteria andMediumStatusBetween(Integer value1, Integer value2) {
            addCriterion("medium_status between", value1, value2, "mediumStatus");
            return (Criteria) this;
        }

        public Criteria andMediumStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("medium_status not between", value1, value2, "mediumStatus");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
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

        public Criteria andOtherTelIsNull() {
            addCriterion("other_tel is null");
            return (Criteria) this;
        }

        public Criteria andOtherTelIsNotNull() {
            addCriterion("other_tel is not null");
            return (Criteria) this;
        }

        public Criteria andOtherTelEqualTo(String value) {
            addCriterion("other_tel =", value, "otherTel");
            return (Criteria) this;
        }

        public Criteria andOtherTelNotEqualTo(String value) {
            addCriterion("other_tel <>", value, "otherTel");
            return (Criteria) this;
        }

        public Criteria andOtherTelGreaterThan(String value) {
            addCriterion("other_tel >", value, "otherTel");
            return (Criteria) this;
        }

        public Criteria andOtherTelGreaterThanOrEqualTo(String value) {
            addCriterion("other_tel >=", value, "otherTel");
            return (Criteria) this;
        }

        public Criteria andOtherTelLessThan(String value) {
            addCriterion("other_tel <", value, "otherTel");
            return (Criteria) this;
        }

        public Criteria andOtherTelLessThanOrEqualTo(String value) {
            addCriterion("other_tel <=", value, "otherTel");
            return (Criteria) this;
        }

        public Criteria andOtherTelLike(String value) {
            addCriterion("other_tel like", value, "otherTel");
            return (Criteria) this;
        }

        public Criteria andOtherTelNotLike(String value) {
            addCriterion("other_tel not like", value, "otherTel");
            return (Criteria) this;
        }

        public Criteria andOtherTelIn(List<String> values) {
            addCriterion("other_tel in", values, "otherTel");
            return (Criteria) this;
        }

        public Criteria andOtherTelNotIn(List<String> values) {
            addCriterion("other_tel not in", values, "otherTel");
            return (Criteria) this;
        }

        public Criteria andOtherTelBetween(String value1, String value2) {
            addCriterion("other_tel between", value1, value2, "otherTel");
            return (Criteria) this;
        }

        public Criteria andOtherTelNotBetween(String value1, String value2) {
            addCriterion("other_tel not between", value1, value2, "otherTel");
            return (Criteria) this;
        }

        public Criteria andShopReadmeIsNull() {
            addCriterion("shop_readme is null");
            return (Criteria) this;
        }

        public Criteria andShopReadmeIsNotNull() {
            addCriterion("shop_readme is not null");
            return (Criteria) this;
        }

        public Criteria andShopReadmeEqualTo(String value) {
            addCriterion("shop_readme =", value, "shopReadme");
            return (Criteria) this;
        }

        public Criteria andShopReadmeNotEqualTo(String value) {
            addCriterion("shop_readme <>", value, "shopReadme");
            return (Criteria) this;
        }

        public Criteria andShopReadmeGreaterThan(String value) {
            addCriterion("shop_readme >", value, "shopReadme");
            return (Criteria) this;
        }

        public Criteria andShopReadmeGreaterThanOrEqualTo(String value) {
            addCriterion("shop_readme >=", value, "shopReadme");
            return (Criteria) this;
        }

        public Criteria andShopReadmeLessThan(String value) {
            addCriterion("shop_readme <", value, "shopReadme");
            return (Criteria) this;
        }

        public Criteria andShopReadmeLessThanOrEqualTo(String value) {
            addCriterion("shop_readme <=", value, "shopReadme");
            return (Criteria) this;
        }

        public Criteria andShopReadmeLike(String value) {
            addCriterion("shop_readme like", value, "shopReadme");
            return (Criteria) this;
        }

        public Criteria andShopReadmeNotLike(String value) {
            addCriterion("shop_readme not like", value, "shopReadme");
            return (Criteria) this;
        }

        public Criteria andShopReadmeIn(List<String> values) {
            addCriterion("shop_readme in", values, "shopReadme");
            return (Criteria) this;
        }

        public Criteria andShopReadmeNotIn(List<String> values) {
            addCriterion("shop_readme not in", values, "shopReadme");
            return (Criteria) this;
        }

        public Criteria andShopReadmeBetween(String value1, String value2) {
            addCriterion("shop_readme between", value1, value2, "shopReadme");
            return (Criteria) this;
        }

        public Criteria andShopReadmeNotBetween(String value1, String value2) {
            addCriterion("shop_readme not between", value1, value2, "shopReadme");
            return (Criteria) this;
        }

        public Criteria andFloorIsNull() {
            addCriterion("floor is null");
            return (Criteria) this;
        }

        public Criteria andFloorIsNotNull() {
            addCriterion("floor is not null");
            return (Criteria) this;
        }

        public Criteria andFloorEqualTo(String value) {
            addCriterion("floor =", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotEqualTo(String value) {
            addCriterion("floor <>", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThan(String value) {
            addCriterion("floor >", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorGreaterThanOrEqualTo(String value) {
            addCriterion("floor >=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThan(String value) {
            addCriterion("floor <", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLessThanOrEqualTo(String value) {
            addCriterion("floor <=", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorLike(String value) {
            addCriterion("floor like", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotLike(String value) {
            addCriterion("floor not like", value, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorIn(List<String> values) {
            addCriterion("floor in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotIn(List<String> values) {
            addCriterion("floor not in", values, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorBetween(String value1, String value2) {
            addCriterion("floor between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andFloorNotBetween(String value1, String value2) {
            addCriterion("floor not between", value1, value2, "floor");
            return (Criteria) this;
        }

        public Criteria andOrienIdIsNull() {
            addCriterion("orien_id is null");
            return (Criteria) this;
        }

        public Criteria andOrienIdIsNotNull() {
            addCriterion("orien_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrienIdEqualTo(String value) {
            addCriterion("orien_id =", value, "orienId");
            return (Criteria) this;
        }

        public Criteria andOrienIdNotEqualTo(String value) {
            addCriterion("orien_id <>", value, "orienId");
            return (Criteria) this;
        }

        public Criteria andOrienIdGreaterThan(String value) {
            addCriterion("orien_id >", value, "orienId");
            return (Criteria) this;
        }

        public Criteria andOrienIdGreaterThanOrEqualTo(String value) {
            addCriterion("orien_id >=", value, "orienId");
            return (Criteria) this;
        }

        public Criteria andOrienIdLessThan(String value) {
            addCriterion("orien_id <", value, "orienId");
            return (Criteria) this;
        }

        public Criteria andOrienIdLessThanOrEqualTo(String value) {
            addCriterion("orien_id <=", value, "orienId");
            return (Criteria) this;
        }

        public Criteria andOrienIdLike(String value) {
            addCriterion("orien_id like", value, "orienId");
            return (Criteria) this;
        }

        public Criteria andOrienIdNotLike(String value) {
            addCriterion("orien_id not like", value, "orienId");
            return (Criteria) this;
        }

        public Criteria andOrienIdIn(List<String> values) {
            addCriterion("orien_id in", values, "orienId");
            return (Criteria) this;
        }

        public Criteria andOrienIdNotIn(List<String> values) {
            addCriterion("orien_id not in", values, "orienId");
            return (Criteria) this;
        }

        public Criteria andOrienIdBetween(String value1, String value2) {
            addCriterion("orien_id between", value1, value2, "orienId");
            return (Criteria) this;
        }

        public Criteria andOrienIdNotBetween(String value1, String value2) {
            addCriterion("orien_id not between", value1, value2, "orienId");
            return (Criteria) this;
        }

        public Criteria andLoopLineIdIsNull() {
            addCriterion("loop_line_id is null");
            return (Criteria) this;
        }

        public Criteria andLoopLineIdIsNotNull() {
            addCriterion("loop_line_id is not null");
            return (Criteria) this;
        }

        public Criteria andLoopLineIdEqualTo(String value) {
            addCriterion("loop_line_id =", value, "loopLineId");
            return (Criteria) this;
        }

        public Criteria andLoopLineIdNotEqualTo(String value) {
            addCriterion("loop_line_id <>", value, "loopLineId");
            return (Criteria) this;
        }

        public Criteria andLoopLineIdGreaterThan(String value) {
            addCriterion("loop_line_id >", value, "loopLineId");
            return (Criteria) this;
        }

        public Criteria andLoopLineIdGreaterThanOrEqualTo(String value) {
            addCriterion("loop_line_id >=", value, "loopLineId");
            return (Criteria) this;
        }

        public Criteria andLoopLineIdLessThan(String value) {
            addCriterion("loop_line_id <", value, "loopLineId");
            return (Criteria) this;
        }

        public Criteria andLoopLineIdLessThanOrEqualTo(String value) {
            addCriterion("loop_line_id <=", value, "loopLineId");
            return (Criteria) this;
        }

        public Criteria andLoopLineIdLike(String value) {
            addCriterion("loop_line_id like", value, "loopLineId");
            return (Criteria) this;
        }

        public Criteria andLoopLineIdNotLike(String value) {
            addCriterion("loop_line_id not like", value, "loopLineId");
            return (Criteria) this;
        }

        public Criteria andLoopLineIdIn(List<String> values) {
            addCriterion("loop_line_id in", values, "loopLineId");
            return (Criteria) this;
        }

        public Criteria andLoopLineIdNotIn(List<String> values) {
            addCriterion("loop_line_id not in", values, "loopLineId");
            return (Criteria) this;
        }

        public Criteria andLoopLineIdBetween(String value1, String value2) {
            addCriterion("loop_line_id between", value1, value2, "loopLineId");
            return (Criteria) this;
        }

        public Criteria andLoopLineIdNotBetween(String value1, String value2) {
            addCriterion("loop_line_id not between", value1, value2, "loopLineId");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIdIsNull() {
            addCriterion("subway_line_id is null");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIdIsNotNull() {
            addCriterion("subway_line_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIdEqualTo(String value) {
            addCriterion("subway_line_id =", value, "subwayLineId");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIdNotEqualTo(String value) {
            addCriterion("subway_line_id <>", value, "subwayLineId");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIdGreaterThan(String value) {
            addCriterion("subway_line_id >", value, "subwayLineId");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIdGreaterThanOrEqualTo(String value) {
            addCriterion("subway_line_id >=", value, "subwayLineId");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIdLessThan(String value) {
            addCriterion("subway_line_id <", value, "subwayLineId");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIdLessThanOrEqualTo(String value) {
            addCriterion("subway_line_id <=", value, "subwayLineId");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIdLike(String value) {
            addCriterion("subway_line_id like", value, "subwayLineId");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIdNotLike(String value) {
            addCriterion("subway_line_id not like", value, "subwayLineId");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIdIn(List<String> values) {
            addCriterion("subway_line_id in", values, "subwayLineId");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIdNotIn(List<String> values) {
            addCriterion("subway_line_id not in", values, "subwayLineId");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIdBetween(String value1, String value2) {
            addCriterion("subway_line_id between", value1, value2, "subwayLineId");
            return (Criteria) this;
        }

        public Criteria andSubwayLineIdNotBetween(String value1, String value2) {
            addCriterion("subway_line_id not between", value1, value2, "subwayLineId");
            return (Criteria) this;
        }

        public Criteria andSuccessFeeIsNull() {
            addCriterion("success_fee is null");
            return (Criteria) this;
        }

        public Criteria andSuccessFeeIsNotNull() {
            addCriterion("success_fee is not null");
            return (Criteria) this;
        }

        public Criteria andSuccessFeeEqualTo(String value) {
            addCriterion("success_fee =", value, "successFee");
            return (Criteria) this;
        }

        public Criteria andSuccessFeeNotEqualTo(String value) {
            addCriterion("success_fee <>", value, "successFee");
            return (Criteria) this;
        }

        public Criteria andSuccessFeeGreaterThan(String value) {
            addCriterion("success_fee >", value, "successFee");
            return (Criteria) this;
        }

        public Criteria andSuccessFeeGreaterThanOrEqualTo(String value) {
            addCriterion("success_fee >=", value, "successFee");
            return (Criteria) this;
        }

        public Criteria andSuccessFeeLessThan(String value) {
            addCriterion("success_fee <", value, "successFee");
            return (Criteria) this;
        }

        public Criteria andSuccessFeeLessThanOrEqualTo(String value) {
            addCriterion("success_fee <=", value, "successFee");
            return (Criteria) this;
        }

        public Criteria andSuccessFeeLike(String value) {
            addCriterion("success_fee like", value, "successFee");
            return (Criteria) this;
        }

        public Criteria andSuccessFeeNotLike(String value) {
            addCriterion("success_fee not like", value, "successFee");
            return (Criteria) this;
        }

        public Criteria andSuccessFeeIn(List<String> values) {
            addCriterion("success_fee in", values, "successFee");
            return (Criteria) this;
        }

        public Criteria andSuccessFeeNotIn(List<String> values) {
            addCriterion("success_fee not in", values, "successFee");
            return (Criteria) this;
        }

        public Criteria andSuccessFeeBetween(String value1, String value2) {
            addCriterion("success_fee between", value1, value2, "successFee");
            return (Criteria) this;
        }

        public Criteria andSuccessFeeNotBetween(String value1, String value2) {
            addCriterion("success_fee not between", value1, value2, "successFee");
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

        public Criteria andPublishedTimeIsNull() {
            addCriterion("published_time is null");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeIsNotNull() {
            addCriterion("published_time is not null");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeEqualTo(Date value) {
            addCriterion("published_time =", value, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeNotEqualTo(Date value) {
            addCriterion("published_time <>", value, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeGreaterThan(Date value) {
            addCriterion("published_time >", value, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("published_time >=", value, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeLessThan(Date value) {
            addCriterion("published_time <", value, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeLessThanOrEqualTo(Date value) {
            addCriterion("published_time <=", value, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeIn(List<Date> values) {
            addCriterion("published_time in", values, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeNotIn(List<Date> values) {
            addCriterion("published_time not in", values, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeBetween(Date value1, Date value2) {
            addCriterion("published_time between", value1, value2, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andPublishedTimeNotBetween(Date value1, Date value2) {
            addCriterion("published_time not between", value1, value2, "publishedTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeIsNull() {
            addCriterion("success_time is null");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeIsNotNull() {
            addCriterion("success_time is not null");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeEqualTo(Date value) {
            addCriterion("success_time =", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeNotEqualTo(Date value) {
            addCriterion("success_time <>", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeGreaterThan(Date value) {
            addCriterion("success_time >", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("success_time >=", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeLessThan(Date value) {
            addCriterion("success_time <", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeLessThanOrEqualTo(Date value) {
            addCriterion("success_time <=", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeIn(List<Date> values) {
            addCriterion("success_time in", values, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeNotIn(List<Date> values) {
            addCriterion("success_time not in", values, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeBetween(Date value1, Date value2) {
            addCriterion("success_time between", value1, value2, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeNotBetween(Date value1, Date value2) {
            addCriterion("success_time not between", value1, value2, "successTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNull() {
            addCriterion("check_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("check_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("check_time =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("check_time <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("check_time >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("check_time >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("check_time <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("check_time <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("check_time in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("check_time not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("check_time between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("check_time not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeIsNull() {
            addCriterion("recommend_type is null");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeIsNotNull() {
            addCriterion("recommend_type is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeEqualTo(String value) {
            addCriterion("recommend_type =", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeNotEqualTo(String value) {
            addCriterion("recommend_type <>", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeGreaterThan(String value) {
            addCriterion("recommend_type >", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeGreaterThanOrEqualTo(String value) {
            addCriterion("recommend_type >=", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeLessThan(String value) {
            addCriterion("recommend_type <", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeLessThanOrEqualTo(String value) {
            addCriterion("recommend_type <=", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeLike(String value) {
            addCriterion("recommend_type like", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeNotLike(String value) {
            addCriterion("recommend_type not like", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeIn(List<String> values) {
            addCriterion("recommend_type in", values, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeNotIn(List<String> values) {
            addCriterion("recommend_type not in", values, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeBetween(String value1, String value2) {
            addCriterion("recommend_type between", value1, value2, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeNotBetween(String value1, String value2) {
            addCriterion("recommend_type not between", value1, value2, "recommendType");
            return (Criteria) this;
        }

        public Criteria andStreetIdIsNull() {
            addCriterion("street_id is null");
            return (Criteria) this;
        }

        public Criteria andStreetIdIsNotNull() {
            addCriterion("street_id is not null");
            return (Criteria) this;
        }

        public Criteria andStreetIdEqualTo(Integer value) {
            addCriterion("street_id =", value, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdNotEqualTo(Integer value) {
            addCriterion("street_id <>", value, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdGreaterThan(Integer value) {
            addCriterion("street_id >", value, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("street_id >=", value, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdLessThan(Integer value) {
            addCriterion("street_id <", value, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdLessThanOrEqualTo(Integer value) {
            addCriterion("street_id <=", value, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdIn(List<Integer> values) {
            addCriterion("street_id in", values, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdNotIn(List<Integer> values) {
            addCriterion("street_id not in", values, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdBetween(Integer value1, Integer value2) {
            addCriterion("street_id between", value1, value2, "streetId");
            return (Criteria) this;
        }

        public Criteria andStreetIdNotBetween(Integer value1, Integer value2) {
            addCriterion("street_id not between", value1, value2, "streetId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNull() {
            addCriterion("province_id is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNotNull() {
            addCriterion("province_id is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdEqualTo(Integer value) {
            addCriterion("province_id =", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotEqualTo(Integer value) {
            addCriterion("province_id <>", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThan(Integer value) {
            addCriterion("province_id >", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("province_id >=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThan(Integer value) {
            addCriterion("province_id <", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThanOrEqualTo(Integer value) {
            addCriterion("province_id <=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIn(List<Integer> values) {
            addCriterion("province_id in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotIn(List<Integer> values) {
            addCriterion("province_id not in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdBetween(Integer value1, Integer value2) {
            addCriterion("province_id between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("province_id not between", value1, value2, "provinceId");
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