package com.dapeng.demo.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class WorkSituationBatchDTO {

    private String studentId;

    private List<WorkSituationDTO> works;

}
