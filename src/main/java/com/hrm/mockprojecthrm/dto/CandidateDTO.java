package com.hrm.mockprojecthrm.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CandidateDTO {
    private  Long id;
    private String name;

    private String fileCv;

    private LocalDate interviewDate;

    private Integer status;

    private String comment;

    private Boolean isDelete;

    private Long userId;

    private Long positionId;

}
