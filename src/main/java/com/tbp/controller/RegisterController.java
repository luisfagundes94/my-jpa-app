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
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String registerUserPage() {
        return "user/register";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public void registerUser(@RequestParam("name") String name,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             Map<String, Object> model) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
    }

}
