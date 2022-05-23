package com.ntqsolution.demo.repository;

import com.ntqsolution.demo.entity.StatusEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusEmployeeRepository extends JpaRepository<StatusEmployee, Long> {
}
