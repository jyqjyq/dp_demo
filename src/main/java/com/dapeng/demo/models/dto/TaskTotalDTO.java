package com.dapeng.demo.models.dto;

import lombok.Data;

@Data
public class TaskTotalDTO {

    private Integer workTotal = 0;

    private Integer mustHandInWorkTotal = 0;

    private Integer completedWorkCount = 0;

    private Integer mustHandInCompletedWorkCount = 0;

}
