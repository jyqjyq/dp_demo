package com.dapeng.demo.models.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel(value = "分页参数模型")
public class PageParam {

    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码不能小于1")
    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNumber;

    @NotNull(message = "每页显示数量不能为空")
    @Min(value = 1, message = "每页显示数量不能小于1")
    @ApiModelProperty(value = "每页显示数量", required = true)
    private Integer pageSize;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
