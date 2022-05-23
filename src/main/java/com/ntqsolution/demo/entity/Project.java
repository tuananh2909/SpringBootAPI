package com.ntqsolution.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private StatusProject status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private LocalDate endDate;

    @JsonIgnore
    @OneToMany(mappedBy = "project")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<EmployeeProject> projectEmployees = new LinkedHashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "project_language",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Language> languages = new LinkedHashSet<>();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "update_at")
    private LocalDate updateAt;
}
