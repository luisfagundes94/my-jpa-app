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
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserSession userSession;

    @RequestMapping(value = "user/login/doLogin", method = RequestMethod.GET)
    public String loginPage() {
        return "user/login";
    }

    @RequestMapping(value = "user/login/doLogin", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Map<String, Object> model) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            userSession.addLoggedUser(user);
            model.put("message", "Usuário autenticado!");
            return "user/login";
        } else {
            model.put("message", "Login inválido");
            return "user/login";
        }
    }

    @RequestMapping(value = "doLogin", method = RequestMethod.GET)
    public String logout() {
        userSession.removeLoggedUser();
        return "user/login";
    }


}
