package com.example.account.controller;

import com.example.account.DA.*;
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

    //curl http://localhost:9091/api/users
    @GetMapping("/users")
    public List<User> list() {
        return usersService.listAll();
    }

    //curl http://localhost:9091/api/users/1
    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        try {
            User user = usersService.get(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
       catch (NoSuchElementException ex) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
       }
    }

    //curl -X POST -H "Content-Type: application/json" -d "{\"name\":\"beti\",\"sex\":0}" http://localhost:9091/api/addUser
    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) {
        usersService.save(user);
    }

//https://www.codejava.net/frameworks/spring-boot/spring-boot-restful-crud-api-examples-with-mysql-database
}
