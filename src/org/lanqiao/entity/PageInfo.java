package org.lanqiao.entity;

import java.util.ArrayList;
import java.util.List;

public class PageInfo<T> {
	private int pageSize;//每页多少条
	private int pageIndex;//当前页的页码；从1开始
	private int totalNumber;//总记录数
	private List<T> datas=new ArrayList<T>();
	
	//只需要一个get方法即可
	private int totalPages;//总页数
	private boolean isFirstPage;//是否是第一页
	private boolean isLastPage;//是否是最后一页
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean getIsFirstPage() {
		return isFirstPage;
	}
	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}
	public boolean getIsLastPage() {
		return isLastPage;
	}
	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
	@Override
	public String toString() {
		return "PageInfo [pageSize=" + pageSize + ", pageIndex=" + pageIndex
				+ ", totalNumber=" + totalNumber + ", datas=" + datas
				+ ", totalPages=" + totalPages + ", isFirstPage=" + isFirstPage
				+ ", isLastPage=" + isLastPage + "]";
	}
	
	
}
