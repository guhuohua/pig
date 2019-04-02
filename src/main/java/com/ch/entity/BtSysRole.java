package com.ch.entity;

import java.util.Date;

public class BtSysRole {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bt_sys_role.role_id
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    private String roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bt_sys_role.role_name
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    private String roleName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bt_sys_role.role_desc
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    private String roleDesc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bt_sys_role.create_time
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bt_sys_role.update_time
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bt_sys_role.del_flag
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    private String delFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bt_sys_role.parent_id
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    private Integer parentId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bt_sys_role.role_id
     *
     * @return the value of bt_sys_role.role_id
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bt_sys_role.role_id
     *
     * @param roleId the value for bt_sys_role.role_id
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bt_sys_role.role_name
     *
     * @return the value of bt_sys_role.role_name
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bt_sys_role.role_name
     *
     * @param roleName the value for bt_sys_role.role_name
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bt_sys_role.role_desc
     *
     * @return the value of bt_sys_role.role_desc
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bt_sys_role.role_desc
     *
     * @param roleDesc the value for bt_sys_role.role_desc
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bt_sys_role.create_time
     *
     * @return the value of bt_sys_role.create_time
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bt_sys_role.create_time
     *
     * @param createTime the value for bt_sys_role.create_time
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bt_sys_role.update_time
     *
     * @return the value of bt_sys_role.update_time
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bt_sys_role.update_time
     *
     * @param updateTime the value for bt_sys_role.update_time
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bt_sys_role.del_flag
     *
     * @return the value of bt_sys_role.del_flag
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bt_sys_role.del_flag
     *
     * @param delFlag the value for bt_sys_role.del_flag
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bt_sys_role.parent_id
     *
     * @return the value of bt_sys_role.parent_id
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bt_sys_role.parent_id
     *
     * @param parentId the value for bt_sys_role.parent_id
     *
     * @mbg.generated Wed Mar 13 11:57:15 CST 2019
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}