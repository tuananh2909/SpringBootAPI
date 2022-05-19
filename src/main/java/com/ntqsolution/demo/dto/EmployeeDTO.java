package com.ntqsolution.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private StatusEmployeeDTO status;
    private LocalDate startDate;
    private Boolean isOut;
    private RoleDTO role;
    private LocalDate updateAt;
}
