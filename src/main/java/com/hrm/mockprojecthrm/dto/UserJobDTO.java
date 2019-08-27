package com.hrm.mockprojecthrm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserJobDTO {
    private Long id;
    private Long manager_id;
    private LocalDate hireDate;
    private Long department;
    private Long position;
}
