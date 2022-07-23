package com.junttest.SBJunit.controllerTest;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.yaml.snakeyaml.util.ArrayUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junttest.SBJunit.controller.EmpController;
import com.junttest.SBJunit.entity.Employee;
import com.junttest.SBJunit.service.EmpService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=EmpController.class)
public class EmpControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmpService empService;
	
	private String response = "{\"id\":5,\"name\":\"Rajjj\"}";
	private String response2 ="[{\"id\":1,\"name\":\"Rajuww\"},{\"id\":5,\"name\":\"Rajjj\"}]";
	
	private Employee mockEmp (String response) throws JsonMappingException, JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper();
		Employee emp = mapper.readValue(response,Employee.class);
		return emp;
	}
	
	private List<Employee> mockEmp2 (String response2) throws JsonMappingException, JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper();
		List<Employee> emplist = mapper.readValue(response2, List.class);
		return emplist;
	}
	
	
	
	/*
	 * @Test public void getEmployeeById() throws Exception { Optional<Employee>
	 * empdata = Optional.of(mockEmp(response));
	 * Mockito.when(empService.getEmployeeById(new Long("5"))).thenReturn(empdata);
	 * this.mockMvc.perform(get("/employee/5")).andExpect(status().isOk()).andExpect
	 * (jsonPath("id").value(5)); assertEquals(empdata.get().getId(), new
	 * Long("5")); verify(empService,times(1)).getEmployeeById(new Long("5")); }
	 */
	
	@Test
	public void getData() throws Exception
	{
		Mockito.when(empService.getData()).thenReturn("Employee Tested");
		this.mockMvc.perform(get("/test")).andExpect(status().isOk());
		assertEquals(empService.getData(), "Employee Tested");
	}
	
	@Test
	public void getAllEmployee() throws Exception
	{
		List<Employee> empList = new ArrayList<Employee>();
		empList.addAll(mockEmp2(response2));
		Mockito.when(empService.getAllEmployee()).thenReturn(empList);
		this.mockMvc.perform(get("/employee")).andExpect(status().isOk()).andExpect(jsonPath("$[1].id").value(5));
		verify(empService,times(1)).getAllEmployee();
	}
	
	@Test 
	public void createEmp() throws Exception
	{
		Employee emp = new Employee(null, response);
		Mockito.when(empService.createEmp(Mockito.any(Employee.class))).thenReturn(emp);
		this.mockMvc.perform(post("/createemployee")
				.accept(MediaType.APPLICATION_JSON)
				.content(response)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());				
	}
	
	@Test
	public void deleteEmp() throws Exception
	{
		this.mockMvc.perform(delete("/deleteemp/{id}","2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		//verify(empService,times(1)).deleteEmp(anything());
	}
}
