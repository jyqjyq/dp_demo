package com.dapeng.demo.models.resp.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @公司: 大鹏科技
 * @创建人: 杨业韬
 * @日期: 2019/3/21 0021 11:27
 * @描述: 基础饼图返回对象
 */
@Data
@ApiModel
public class BasePieResp {
    @ApiModelProperty(value = "对应饼图的占比说明")
    private String name;

    @ApiModelProperty(value = "对应具体的占比值")
    private Number  value;
}
