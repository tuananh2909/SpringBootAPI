package com.ntqsolution.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private StatusProjectDTO status;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate updateAt;

}
