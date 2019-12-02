package com.dapeng.demo.models.dto.in;

import lombok.Data;

@Data
public class AuditionCountBatchInDTO {

    private String stageIds;

    private Integer pageSize = 10;

    private Integer pageNumber = 1;


}
