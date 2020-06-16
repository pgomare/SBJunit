package com.junttest.SBJunit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.junttest.SBJunit.entity.Employee;
import com.junttest.SBJunit.service.EmpService;

@RestController
public class EmpController {

	@Autowired
	private EmpService empservice;
	
	@GetMapping("/test")
	public String getData()
	{
		return empservice.getData();
	}
	
	@GetMapping("/employee")
	public List<Employee> getAllStudent()
	{
		return empservice.getAllEmployee();
	}
	
	@GetMapping("/employee/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable Long id)
	{
		return empservice.getEmployeeById(id);
	}
	
	@PostMapping(path="/createemployee")
	public Employee createEmp(@RequestBody Employee employee)
	{
		return empservice.createEmp(employee);
	}
	
	@PutMapping(path="/updateemployee/{id}")
	public Employee updateEmp(@RequestBody Employee employee,@PathVariable Long id)
	{
		return empservice.updateEmp(employee,id);
	}
	
	@DeleteMapping("/deleteemp/{id}")
	public void deleteEmp(@PathVariable Long id)
	{
		empservice.deleteEmp(id);
	}
	
}
