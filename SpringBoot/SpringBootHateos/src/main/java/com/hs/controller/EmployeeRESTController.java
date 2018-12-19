package com.hs.controller;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.model.EmployeeListVO;
import com.hs.model.EmployeeReport;
import com.hs.model.EmployeeVO;
import com.hs.repository.EmployeeDB;

@RestController
public class EmployeeRESTController {

	@RequestMapping(value = "/employees")
	public EmployeeListVO getAllEmployees() {
		EmployeeListVO employeesList = new EmployeeListVO();

		for (EmployeeVO employee : EmployeeDB.getEmployeeList()) {
			// Adding self link employee 'singular' resource
			Link link = ControllerLinkBuilder.linkTo(EmployeeRESTController.class).slash(employee.getEmployeeId())
					.withSelfRel();

			// Add link to singular resource
			employee.add(link);

			// Adding method link employee 'singular' resource
			ResponseEntity<EmployeeReport> methodLinkBuilder = ControllerLinkBuilder
					.methodOn(EmployeeRESTController.class).getReportByEmployeeById(employee.getEmployeeId());
			Link reportLink = ControllerLinkBuilder.linkTo(methodLinkBuilder).withRel("employee-report");

			// Add link to singular resource
			employee.add(reportLink);

			employeesList.getEmployees().add(employee);
		}

		// Adding self link employee collection resource
		Link selfLink = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(EmployeeRESTController.class).getAllEmployees()).withSelfRel();

		// Add link to collection resource
		employeesList.add(selfLink);

		return employeesList;
	}

	@RequestMapping(value = "/employees/{id}")
	public ResponseEntity<EmployeeVO> getEmployeeById (@PathVariable("id") int id)
	{
	    if (id <= 3) {
	        EmployeeVO employee = EmployeeDB.getEmployeeList().get(id-1);
	         
	        //Self link
	        Link selfLink = ControllerLinkBuilder
	                .linkTo(EmployeeRESTController.class)
	                .slash(employee.getEmployeeId())
	                .withSelfRel();
	         
	        //Method link
	        Link reportLink = ControllerLinkBuilder
	                .linkTo(ControllerLinkBuilder.methodOn(EmployeeRESTController.class)
	                .getReportByEmployeeById(employee.getEmployeeId()))
	                .withRel("report");
	         
	        employee.add(selfLink);
	        employee.add(reportLink);
	        return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
	    }
	    return new ResponseEntity<EmployeeVO>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/employees/{id}/report")
	public ResponseEntity<EmployeeReport> getReportByEmployeeById(@PathVariable("id") int id) {
		// Do some operation and return report
		return null;
	}
}