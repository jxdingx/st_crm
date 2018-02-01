package com.core.model;

public class BaseModel extends PagerModel {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String createTime; // 创建时间
	private String updateTime; // 更新时间
	private Integer createBy; // 创建人（一般为用户表主键）
	private Integer updateBy; // 更新人（一般为用户表主键）
	private Double orderBy; // 排序序号（小数类型）
	private String isdelete;
	private Integer iseffect;
	private String descr; // 描述
	private String status;
	private String remark;

	public Integer getId() {
		System.out.println("ssssssss");
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Double getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Double orderBy) {
		this.orderBy = orderBy;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}

	public Integer getIseffect() {
		return iseffect;
	}

	public void setIseffect(Integer iseffect) {
		this.iseffect = iseffect;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
