package com.dapeng.demo.models.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class AuditionCourseListResp {
	
	@ApiModelProperty(value = "部门")
	private String dept;
	
	@ApiModelProperty(value = "学院")
    private String college;
	
	@ApiModelProperty(value = "战队")
    private String team;
	
	@ApiModelProperty(value = "小组")
    private String group;
	
	@ApiModelProperty(value = "工号")
    private String employeeId;
	
	@ApiModelProperty(value = "所属顾问")
    private String consultant;
	
	@ApiModelProperty(value = "班级代号")
    private String classCode;
	
	@ApiModelProperty(value = "课程名称")
	private String courseName;
	
	@ApiModelProperty(value = "用户信息")
	private String studentName;
	
	@ApiModelProperty(value = "手机号")
	private String mobile;
	
	@ApiModelProperty(value = "上课时间")
	private String auditionBeginTime;
	
	@ApiModelProperty(value = "上课时长（分）")
	private String minute;
	
	@ApiModelProperty(value = "听课时长")
	private String minuteRender;
	
	@ApiModelProperty(value = "类型")
	private String flowType;
	
	@ApiModelProperty(value = "账号")
	private String flowAccount;

	@ApiModelProperty(value = "大鹏号")
	private String dapengNum;

}
