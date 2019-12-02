package com.dapeng.demo.models.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class ListenSituationResultResp {

    @ApiModelProperty(value = "数据")
    private List<ListenCourseResp> result;

    @ApiModelProperty(value = "总条数")
    private String total;

//    @ApiModelProperty(value = "当前页")
//    private String page;
//
//    @ApiModelProperty(value = "页面大小")
//    private String size;

    @ApiModelProperty(value = "总页数")
    private String totalPageNum;

}
