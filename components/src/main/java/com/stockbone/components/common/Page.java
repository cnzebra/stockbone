package com.stockbone.components.common;

import java.util.List;

public class Page<T> {
	/**
	 * 当前页数
	 */
	private int currentPage = 1;
	/**
	 * 每页大小
	 */
	private int pageSize = 10;
	/**
	 * 总记录数
	 */
	private int totalSize = 0;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 内容
	 */
	private List<T> content;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

}
