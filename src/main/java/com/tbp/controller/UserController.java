package com.tbp.controller;

import com.tbp.model.User;
import com.tbp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/user/save", method = RequestMethod.GET)
    public User save(@RequestParam("name") String name, @RequestParam("email") String email,
                     @RequestParam("password") String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        System.out.println("deu certo!");
        return user;
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.GET)
    public User update(@RequestParam("name") String name, @RequestParam("email") String email,
                       @RequestParam("password") String password, @RequestParam("id") Long id) {
        User user = userRepository.findOne(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }


}
