package com.dapeng.demo.models.dto;

import lombok.Data;

@Data
public class SubmitCountBatchDTO {

    private String userId;

    private Integer workNumCompleted;

    private String stageId;

}
