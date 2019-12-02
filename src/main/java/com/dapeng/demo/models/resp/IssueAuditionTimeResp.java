package com.dapeng.demo.models.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class IssueAuditionTimeResp {

    @ApiModelProperty(value = "直播数据")
    private Integer liveDurationTotal;

    @ApiModelProperty(value = "回放数据")
    private Integer playBackDurationTotal;


}
