package com.tbp.controller;

import com.tbp.model.User;
import com.tbp.repository.UserRepository;
import com.tbp.sessions.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserSession userSession;

    @RequestMapping(value = "doLogin", method = RequestMethod.GET)
    public String loginPage() {
        return "login/doLogin";
    }


}
