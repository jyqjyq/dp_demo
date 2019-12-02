package com.dapeng.demo.models.dto.out;

import lombok.Data;

import java.util.List;

@Data
public class WorkSituationByCourseOutDTO {

    private String stageId;

    private List<WorkSituationByCourseInfoOutDTO> works;

}
