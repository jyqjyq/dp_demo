package com.dapeng.demo.models.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class AuditionCourseSummaryParam {
	
	@NotBlank(message = "日期不能为空")
    @ApiModelProperty(value = "日期，格式：yyyy-MM-dd", example = "2018-08-12")
    private String date;
	
    @ApiModelProperty(value = "课程名称+' '+上课时间", example = "《岁寒三友》花鸟  14:30")
	private String courseName;
	
	@ApiModelProperty(value = "班级代号，多个则以 英文逗号分隔", example = "LA001,TIAN9902")
	private String classCodes;
	
}
