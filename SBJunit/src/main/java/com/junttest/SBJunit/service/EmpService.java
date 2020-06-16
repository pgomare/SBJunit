package com.junttest.SBJunit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.junttest.SBJunit.entity.Employee;
import com.junttest.SBJunit.ripository.EmpRepository;

@Component
public class EmpService {
	
	@Autowired
	private EmpRepository empRepository;

	public String getData() {
		
		return "Employee Tested";
	}

	public Optional<Employee> getEmployeeById(Long id) {
		
		return empRepository.findById(id);
	}

	public Employee createEmp(Employee employee) {
		return empRepository.saveAndFlush(employee);
	}

	public Employee updateEmp(Employee employee, Long id) {
		employee.setId(id);
		return empRepository.saveAndFlush(employee);
	}

	public void deleteEmp(Long id) {
		empRepository.deleteById(id);
	}

	public List<Employee> getAllEmployee() {
		
		return empRepository.findAll();
	}

}
