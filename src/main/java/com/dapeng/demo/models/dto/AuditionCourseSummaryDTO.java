package com.dapeng.demo.models.dto;

import lombok.Data;

@Data
public class AuditionCourseSummaryDTO {
	
	private Integer  total;
	
	private Integer  gt30Num;
	
	private Integer  gt15Lt30Num;
	
	private Integer  gt5Lt15Num;
	
	private Integer  lt5Num;

}
