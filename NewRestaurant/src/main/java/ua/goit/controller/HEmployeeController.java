package ua.goit.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.goit.domain.*;
import ua.goit.DAO.EmployeeDao;
import ua.goit.service.EmployeeService;
import ua.goit.service.impl.EmployeeServiceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurant")
public class HEmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private static HttpHeaders responseHeaders = new HttpHeaders();


    @RequestMapping(value = "/employee", method = RequestMethod.PUT, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object addEmployee(@RequestBody Employee employee) {

        employeeService.addEmployee(employee);
        return new ResponseEntity<>("{\"employee\":\"" + employee.getName() + "\"}", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/addWaiter", method = RequestMethod.PUT, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object addWaiter(@RequestBody Waiter waiter) {

        employeeService.addWaiter(waiter);
        return new ResponseEntity<>("{\"waiter\":\"" + waiter.getName() + "\"}", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/addCook", method = RequestMethod.PUT, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object addCook(@RequestBody Cook cook) {

        employeeService.addCook(cook);
        return new ResponseEntity<>("{\"cook\":\"" + cook.getName() + "\"}", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{name}", method = RequestMethod.DELETE, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object deleteEmployee(@PathVariable("name") String name) {

        employeeService.deleteEmployee(name);
        return new ResponseEntity<>("{\"deleted\":\"" + name + "\"}", responseHeaders, HttpStatus.OK);

    }

    @RequestMapping(value = "/employee/{name}", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getByName(@PathVariable("name") String name) {

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(employeeService.getByName(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getAllEmployees() {

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(employeeService.getAllEmployees());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}