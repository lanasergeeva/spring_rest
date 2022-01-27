package com.lana.spring.rest.controller;

import com.lana.spring.rest.entity.Employee;
import com.lana.spring.rest.exception_handling.EmployeeIncorrectData;
import com.lana.spring.rest.exception_handling.NoSuchEmployeeException;
import com.lana.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyRestController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("api/employees")
    public List<Employee> showAllEmployee() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }

    @GetMapping("api/employees/{id}")
    public Employee showEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee where id is " + id);
        }
        return employee;
    }

    @PostMapping("api/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("api/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("api/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if(employee == null) {
            throw new NoSuchEmployeeException("There is no Employee with Id =" + id);
        }
        employeeService.deleteEmployee(id);
        return "Employee with id " + id + " was deleted ";
    }

   /* @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handlerException(NoSuchEmployeeException exception) {
        EmployeeIncorrectData employeeIncorrectData = new EmployeeIncorrectData();
        employeeIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(employeeIncorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handlerException(Exception exception) {
        EmployeeIncorrectData employeeIncorrectData = new EmployeeIncorrectData();
        employeeIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(employeeIncorrectData, HttpStatus.BAD_REQUEST);
    }*/

}
