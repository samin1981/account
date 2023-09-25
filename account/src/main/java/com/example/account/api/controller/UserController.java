package com.example.account.api.controller;

import com.example.account.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.account.service.UsersService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UsersService usersService;

    //---------------Create CRUD RESTFul Services------------------
    //curl http://localhost:9091/api/getAllUsers
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return usersService.listAll();
    }

    //curl http://localhost:9091/api/getUser/1
    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        try {
            User user = usersService.get(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    //curl -X POST -H "Content-Type: application/json" -d "{\"name\":\"samin\",\"sex\":0}" http://localhost:9091/api/addUser
    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) {
        usersService.save(user);
    }

    //curl -X PUT -H "Content-Type: application/json" -d "{\"id\":2,\"name\":\"shana\",\"sex\":0}" http://localhost:9091/api/updateUser/1
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Integer id) {
        try {
            User existUser = usersService.get(id);
            usersService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //curl -X DELETE http://localhost:9091/api/deleteUser/5
    @DeleteMapping("deleteUser/{id}")
    public void deleteUser(@PathVariable Integer id) {
        usersService.delete(id);
    }

    //------------HelloWorld Swagger-------------
    @Operation(summary = "Get string")
    @RequestMapping(value = "/sayHello", method = RequestMethod.POST)
    public String sayHello() {
        return "Hello Swagger. I am samin..!";
    }

}
