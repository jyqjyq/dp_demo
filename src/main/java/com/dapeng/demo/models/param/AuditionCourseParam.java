package com.dapeng.demo.models.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class AuditionCourseParam {
	
	@NotBlank(message = "日期不能为空")
    @ApiModelProperty(value = "日期，格式：yyyy-MM-dd", example = "2019-04-15")
    private String date;

	@ApiModelProperty(value = "学院",example = "设计学院")
    private String college;

}
