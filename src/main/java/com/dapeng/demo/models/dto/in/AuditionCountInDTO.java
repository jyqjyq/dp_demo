package com.dapeng.demo.models.dto.in;

import lombok.Data;

@Data
public class AuditionCountInDTO {

    private String studentId;

    private String stageId;

    private Integer pageSize = 10;

    private Integer pageNumber = 1;


}
