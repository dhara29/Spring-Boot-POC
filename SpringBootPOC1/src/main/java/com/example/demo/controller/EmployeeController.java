package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmployeeRepo;
import com.example.demo.model.Employee;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepo repo;
	
	@GetMapping(path="/employees", produces= {"application/json"})
//	@ResponseBody					//Returned String is data not view name
	public List<Employee> getEmployees()
	{
		return repo.findAll();
	}
	
	@GetMapping(path="/employee/{id}", produces= {"application/json"})
	public Optional<Employee> getEmployee(@PathVariable ("id") int id)
	{
		return repo.findById(id);	
	}
	
	@PostMapping(path="/employee", consumes={"application/json"})
	public Employee addEmployee(@RequestBody Employee employee)
	{
		repo.save(employee);
		return employee;
	}
	
	@PutMapping(path="/employee", consumes={"application/json"})
	public Employee saveOrUpdateAlien(@RequestBody Employee employee)
	{
		repo.save(employee);
		return employee;
	}
	
	@DeleteMapping("/employee/{id}")
	public String deteleAlien(@PathVariable int id)
	{
		Employee emp  = repo.getOne(id);
		
		repo.delete(emp);
		
		return "Deleted...";
	}
	
}
