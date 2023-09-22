package com.example.account.service;

import com.example.account.DA.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

   public List<User> listAll() {
       return userRepository.findAll();
   }

   public void save(User user) {
       userRepository.save(user);
   }

   public User get(Integer id) {
       return userRepository.findById(id).get();
   }

   public void delete(Integer id) {
       userRepository.deleteById(id);
   }


}
