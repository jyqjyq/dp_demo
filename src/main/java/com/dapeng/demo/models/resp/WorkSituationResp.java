package com.dapeng.demo.models.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class WorkSituationResp {

    @ApiModelProperty(value = "上课日期")
    private String openChapters;

    @ApiModelProperty(value = "作业名称")
    private String workTitle;

    @ApiModelProperty(value = "是否提交")
    private Boolean isHandIn;

    @ApiModelProperty(value = "分数")
    private String score;

    @ApiModelProperty(value = "提交时间")
    private String submitHomeworkTime;

    @ApiModelProperty(value = "是否必交")
    private Boolean isMustHandIn;

}
