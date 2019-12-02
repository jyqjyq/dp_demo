package com.dapeng.demo.models.dto.in;

import lombok.Data;

@Data
public class IssueAuditionCountInDTO {

    private String studentId;

    private String stageId;

    private Integer page = 1;

    private Integer size = 10;

}
