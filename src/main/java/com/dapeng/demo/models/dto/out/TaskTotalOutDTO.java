package com.dapeng.demo.models.dto.out;

import lombok.Data;

@Data
public class TaskTotalOutDTO {

    private Integer workTotal;

    private Integer mustHandInWorkTotal;

    private Integer completedWorkCount;

    private Integer mustHandInCompletedWorkCount;


}
