package com.dapeng.demo.models.dto.out;

import lombok.Data;

@Data
public class SubmitCountBatchOutDTO {

    private String userId;

    private Integer workNumCompleted;

    private String stageId;

}
