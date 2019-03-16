package com.tbp.controller;

import com.tbp.model.User;
import com.tbp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "user/register", method = RequestMethod.GET)
    public String registerPage() {
        return "user/register";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public User register(@RequestParam("name") String name, @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         Map<String, Object> model) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        System.out.println("deu certo!");
        return user;
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public User update(@RequestParam("name") String name, @RequestParam("email") String email,
                       @RequestParam("password") String password, @RequestParam("id") Long id) {
        User user = userRepository.findOne(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }

    @RequestMapping(value = "user/list", method = RequestMethod.GET)
    public String listPage(Map<String, Object> model) {
        Iterable<User> userList = userRepository.findAll();
        model.put("userList", userList);
        System.out.println("Usuarios: " + userList);
        return "user/list";
    }


}
