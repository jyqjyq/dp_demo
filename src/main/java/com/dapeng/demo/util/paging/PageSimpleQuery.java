package com.dapeng.demo.util.paging;

import java.util.ArrayList;
import java.util.List;

public class PageSimpleQuery implements PageQuery{
	private int pageNumber;
	private int pageSize;
	private List<PageOrder> orderBy ;
	
	public PageSimpleQuery() {
		this(0,10,null);
	}
	public PageSimpleQuery(int pageNumber,int pageSize) {
		this(pageNumber,pageSize,null);
	}
	public PageSimpleQuery(int pageNumber,int pageSize,List<PageOrder> orderBy) {
		this.setPageNumber(pageNumber);
		this.setPageSize(pageSize);
		
		if ( orderBy == null) {
			orderBy = new ArrayList<>();
		}
		this.orderBy = orderBy;
	}
	public PageSimpleQuery addOrder(String property,String direction) {
		if ( orderBy != null) {
			orderBy = new ArrayList<>();
		}
		orderBy.add(new PageOrder(property, direction));
		return this;
	}
	
	public int getPageNumber() {
		return pageNumber;
	} 
	public boolean isEmptyOrder() {
		return orderBy == null || orderBy.size() == 0;
	}
	
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<PageOrder> getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(List<PageOrder> orderBy) {
		this.orderBy = orderBy;
	} 
	
	
}
