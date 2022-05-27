package com.ntqsolution.demo.repository;

import com.ntqsolution.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.name LIKE CONCAT('%',:query,'%')")
    List<Employee> searchEmployeesByName(String query);
}
