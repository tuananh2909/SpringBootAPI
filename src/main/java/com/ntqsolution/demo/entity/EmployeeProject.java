package com.ntqsolution.demo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee_project")
@Getter
@Setter
public class EmployeeProject {
    @EmbeddedId
    private EmployeeProjectKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeID")
    @JoinColumn(name = "employee_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectID")
    @JoinColumn(name = "project_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Project project;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_join", nullable = false)
    private LocalDate dateJoin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_out")
    private LocalDate dateOut;

    @Column(name = "is_reject", nullable = false)
    private Boolean isReject;
}
