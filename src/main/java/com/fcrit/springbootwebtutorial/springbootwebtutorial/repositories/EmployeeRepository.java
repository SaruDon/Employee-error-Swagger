package com.fcrit.springbootwebtutorial.springbootwebtutorial.repositories;

import com.fcrit.springbootwebtutorial.springbootwebtutorial.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
