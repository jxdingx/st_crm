package com.core.pager;

import java.io.Serializable;

/**
 * Description: 分页技术的实现。
 */
// oracle,sqlserver,mysql分页技术
public class Pager implements Serializable {

	private static final long serialVersionUID = 1L;

	private int pageId = 1; // 当前页
	private int rowCount = 0; // 总行数
	private int pageSize = 10; // 页大小
	private int pageCount = 0; // 总页数
	private int pageOffset = 0; // 当前页起始记录
	private int pageTail = 0; // 当前页到达的记录
	private String orderField; // 排序字段
	private boolean orderDirection; // 升降序
	private String orderCondition; // 升降序
	private String mysqlQueryCondition;// limit分页
	private int length = 6; // 页面显示分页按钮个数
	private int startIndex = 0; // 开始分页数字
	private int endIndex = 0; // 结束分页数字
	private int[] indexs;

	public Pager() {
		this.orderDirection = true;
	}

	public int[] getIndexs() {
		int len = getEndIndex() - getStartIndex() + 1;
		indexs = new int[len];
		for (int i = 0; i < len; i++) {
			indexs[i] = (getStartIndex() + i);
		}
		return indexs;
	}

	public void setIndexs(int[] indexs) {
		this.indexs = indexs;
	}

	public int getStartIndex() {
		startIndex = pageId - (length / 2);
		if (startIndex < 1) {
			startIndex = 1;
		}
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		if (getStartIndex() < 1) {
			setStartIndex(1);
		}
		endIndex = (getStartIndex() + length) <= getPageCount() ? (getStartIndex() + length) : getPageCount();
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	protected void doPage() {
		pageCount = rowCount / pageSize + 1;
		// 如果模板==0，且总数大于1，则减一
		if ((rowCount % pageSize == 0) && pageCount > 1)
			pageCount--;
		// Mysql 算法
		pageOffset = (pageId - 1) * pageSize;
		pageTail = pageOffset + pageSize;
		if ((pageOffset + pageSize) > rowCount)
			pageTail = rowCount;
	}

	public String getOrderCondition() {
		if (orderField == null || orderField.trim().length() == 0) {
			return "";
		}
		orderCondition = new StringBuffer(" order by ").append(orderField).append(orderDirection ? " " : " desc ")
				.toString();
		return orderCondition;
	}

	public void setOrderCondition(String orderCondition) {
		this.orderCondition = orderCondition;
	}

	public String getMysqlQueryCondition() {
		mysqlQueryCondition = new StringBuffer(" limit ").append(pageOffset).append(",").append(pageSize).toString();
		return mysqlQueryCondition;
	}

	public void setMysqlQueryCondition(String mysqlQueryCondition) {
		this.mysqlQueryCondition = mysqlQueryCondition;
	}

	public boolean isOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(boolean orderDirection) {
		this.orderDirection = orderDirection;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	public int getPageOffset() {
		return pageOffset;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageTail(int pageTail) {
		this.pageTail = pageTail;
	}

	public int getPageTail() {
		return pageTail;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		this.doPage();
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}