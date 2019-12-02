package com.dapeng.demo.models.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel
public class AuditionCourseListParam extends AuditionCourseSummaryParam {
	
	 @ApiModelProperty(value = "排序方式")
	private String sortType;
	
    @ApiModelProperty(value = "页码,默认1")
    private Integer pageNumber = 1;

    @ApiModelProperty(value = "每页显示数量，默认10")
    private Integer pageSize = 10;
	
}
