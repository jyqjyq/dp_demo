package com.dapeng.demo.util.paging;

import java.util.List;

public interface PageQuery {
	public int getPageNumber();
	public void setPageNumber(int pageNumber);
	public int getPageSize();
	public void setPageSize(int pageSize) ;
	public List<PageOrder> getOrderBy();
	public void setOrderBy(List<PageOrder> orderBy) ;
}
