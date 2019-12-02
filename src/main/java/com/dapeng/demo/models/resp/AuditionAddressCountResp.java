package com.dapeng.demo.models.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class AuditionAddressCountResp {

    @ApiModelProperty(value = "地名")
    private String text;

    @ApiModelProperty(value = "数量")
    private Integer data;

    @ApiModelProperty(value = "包含城市")
    private List<AuditionAddressCountResp> details;

}
