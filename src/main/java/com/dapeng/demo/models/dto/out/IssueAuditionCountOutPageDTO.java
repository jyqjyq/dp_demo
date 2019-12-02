package com.dapeng.demo.models.dto.out;

import lombok.Data;

import java.util.List;

@Data
public class IssueAuditionCountOutPageDTO {

    private List<IssueAuditionCountOutDTO> result;

    private String total;

    private String totalPageNum;

}
