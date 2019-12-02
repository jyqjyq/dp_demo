package com.dapeng.demo.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class IssueAuditionCountPageDTO {

    private List<IssueAuditionCountDTO> result;

    private String total;

    private String totalPageNum;

}
