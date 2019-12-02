package com.dapeng.demo.models.dto;

import lombok.Data;

@Data
public class WorkSituationDTO {

    private String userId;

    private String openChapters;

    private String workTitle;

    private Boolean isMustHandIn;

    private Boolean isHandIn;

    private String score;

    private String submitHomeworkTime;

}
