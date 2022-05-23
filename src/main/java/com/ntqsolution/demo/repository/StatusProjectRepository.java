package com.ntqsolution.demo.repository;

import com.ntqsolution.demo.entity.StatusProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusProjectRepository extends JpaRepository<StatusProject, Long> {
}
