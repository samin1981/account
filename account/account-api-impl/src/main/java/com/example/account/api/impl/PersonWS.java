package com.example.account.api.impl;

import com.example.account.api.PersonAPI;
import com.example.account.api.person.GetAllPersonsRequest;
import com.example.account.api.person.GetAllPersonsResult;
import com.example.account.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PersonWS implements PersonAPI {

    @Autowired
    private PersonService usersService;

    @Override
    public GetAllPersonsResult getAllPersons() {
        return usersService.getAllPersons();
    }

//    @RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public ResponseEntity<User> getUser(@PathVariable Integer id) {
//        try {
//            User user = usersService.get(id);
//            return new ResponseEntity<User>(user, HttpStatus.OK);
//        } catch (NoSuchElementException ex) {
//            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    //curl -X POST -H "Content-Type: application/json" -d "{\"name\":\"samin\",\"sex\":0}" http://localhost:9091/api/addUser
//    @PostMapping("/addUser")
//    public void addUser(@RequestBody User user) {
//        usersService.save(user);
//    }
//
//    //curl -X PUT -H "Content-Type: application/json" -d "{\"id\":2,\"name\":\"shana\",\"sex\":0}" http://localhost:9091/api/updateUser/1
//    @PutMapping("/updateUser/{id}")
//    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Integer id) {
//        try {
//            User existUser = usersService.get(id);
//            usersService.save(user);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (NoSuchElementException ex) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    //curl -X DELETE http://localhost:9091/api/deleteUser/5
//    @DeleteMapping("deleteUser/{id}")
//    public void deleteUser(@PathVariable Integer id) {
//        usersService.delete(id);
//    }
//
//    //------------HelloWorld Swagger-------------
//    @Operation(summary = "Get string")
//    @RequestMapping(value = "/sayHello", method = RequestMethod.POST)
//    public String sayHello() {
//        return "Hello Swagger. I am samin..!";
//    }

}
