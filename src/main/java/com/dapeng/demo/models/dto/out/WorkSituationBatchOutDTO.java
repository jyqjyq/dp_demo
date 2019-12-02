package com.dapeng.demo.models.dto.out;

import lombok.Data;

import java.util.List;

@Data
public class WorkSituationBatchOutDTO {

    private String studentId;

    private List<WorkSituationDataOutDTO> works;

}
