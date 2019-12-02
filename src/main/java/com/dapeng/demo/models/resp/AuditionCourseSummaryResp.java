package com.dapeng.demo.models.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class AuditionCourseSummaryResp {
	
	@ApiModelProperty(value = "总数")
	private Integer  total;
	
	@ApiModelProperty(value = "观看时长大于30分钟的数量")
	private Integer  gt30Num;
	
	@ApiModelProperty(value = "观看时长15到30分钟的数量")
	private Integer  gt15Lt30Num;
	
	@ApiModelProperty(value = "观看时长  5到15分钟的数量")
	private Integer  gt5Lt15Num;
	
	@ApiModelProperty(value = "观看时长小于5分钟的数量")
	private Integer  lt5Num;

}
