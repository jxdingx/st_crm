package com.core.model;

import java.io.Serializable;

import com.core.pager.Pager;

public class PagerModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer page = 1;
	private Integer rows = 10;
	private String sort;
	private String order;
	private Pager pager = new Pager();// 分页导航

	public Pager getPager() {
		pager.setPageId(page);
		pager.setPageSize(rows);
		// if (sort != null && sort.trim().length() != 0) {
		if (order != null && order.trim().length() != 0) {
			pager.setOrderField(order);
			// pager.setOrderField(sort + " " + order);
			// } else {
			// pager.setOrderField(sort);
		}
		// }
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
