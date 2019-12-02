package com.dapeng.demo.util.paging;

public class PageOrder {
	public final static String ASC = "ASC";
	
	public final static String DESC = "DESC";
	
	/**
	 * ASD 或者
	 */
	private String direction;
	
	private String property;
	
	public PageOrder() {
		
	}
	public PageOrder(String property) {
		 this(property,DESC);
	}
	public PageOrder(String property,String direction) {
		this.setProperty(property);
		this.setDirection(direction);
	}
	

	public String getDirection() {
		return direction;
	}

	public PageOrder setDirection(String direction) {
		if (direction == null ) {
			throw new IllegalArgumentException(" argument direction must be not null"); 
		}else {
			direction = direction.toUpperCase();
			if ( ! ASC.equals(direction) && !DESC.equals(direction)) {
				throw new IllegalArgumentException("direction must equal ASC or DESC"); 
			}
		}  
		this.direction = direction;
		return this;
	}

	public String getProperty() {
		return property;
	}

	public PageOrder setProperty(String property) {
		this.property = property;
		
		return this;
	}
	
	
}
