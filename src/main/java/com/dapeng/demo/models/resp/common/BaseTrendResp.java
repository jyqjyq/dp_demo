package com.dapeng.demo.models.resp.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @公司: 大鹏科技
 * @创建人: 杨业韬
 * @日期: 2019/3/21 0021 11:16
 * @描述: 基础折线/柱状图返回对象
 */
@Data
@ApiModel
public class BaseTrendResp {

    @ApiModelProperty(value = "线段名称集")
    private List<String> legend;

    @ApiModelProperty(value = "x轴数据集")
    private List<String> xData;

    @ApiModelProperty(value = "y轴线段值的集合")
    private List<List<Long>> data;
}
