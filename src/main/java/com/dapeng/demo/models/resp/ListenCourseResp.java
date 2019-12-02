package com.dapeng.demo.models.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ListenCourseResp {

    @ApiModelProperty(value = "章节名")
    private String openChapters;

    @ApiModelProperty(value = "上课日期")
    private String teachingTime;

    @ApiModelProperty(value = "课程时长")
    private Integer courseDuration;

    @ApiModelProperty(value = "听课时长")
    private Integer liveDuration;

    @ApiModelProperty(value = "直播回放听课时长")
    private Integer playBackDuration;

}
