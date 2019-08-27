package com.hrm.mockprojecthrm.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class LeaveDTO {

    @NotNull
    private Long id;

    @NotNull
    private Long leaveTypeId;

    @NotNull
    private Long userId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private Integer status;

    @NotNull
    private String comment;

    @NotNull
    private Boolean isDelete;

    private Instant createAt;

    private Long createBy;

    private String name;

    private String nameLeaveType;
}
