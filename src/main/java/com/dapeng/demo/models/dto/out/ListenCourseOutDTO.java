package com.dapeng.demo.models.dto.out;

import lombok.Data;

import java.util.List;

@Data
public class ListenCourseOutDTO {

    private List<ListenCourseDataOutDTO> result;

    private String total;

//    @ApiModelProperty(value = "当前页")
//    private String page;
//
//    @ApiModelProperty(value = "页面大小")
//    private String size;

    private String totalPageNum;

}
