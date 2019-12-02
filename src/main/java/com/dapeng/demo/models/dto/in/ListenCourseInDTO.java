package com.dapeng.demo.models.dto.in;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListenCourseInDTO implements Serializable{

    private String stageId;

    private String studentId;

    private Integer page;

    private Integer size = 10;

}
