package com.hrm.mockprojecthrm.dto;

import com.hrm.mockprojecthrm.entity.Nationality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String name;

    private Boolean gender;

    private Boolean maritalStatus;

    private LocalDate birthday;

    private Long nationality;

    private String author;

    private String positionName;

    private String departmentName;

    private String managerName;

    private LocalDate hireDate;

    private String email;

    private String nationalName;

}
