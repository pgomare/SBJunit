package com.junttest.SBJunit.ripository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.junttest.SBJunit.entity.Employee;

@Repository
public interface EmpRepository extends JpaRepository<Employee, Long>{

}
