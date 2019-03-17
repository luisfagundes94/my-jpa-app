package com.tbp.controller;

import com.tbp.model.User;
import com.tbp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "user/success", method = RequestMethod.GET)
    public String successPage(Map<String, Object> model, @RequestParam(value = "registeredName", required = false) String name,
                                                         @RequestParam(value = "registeredEmail", required = false) String email) {
        if (name != null && email != null) {
            model.put("registeredName", name);
            model.put("registeredEmail", email);
        }
        return "user/success";
    }

    @RequestMapping(value = "user/register", method = RequestMethod.GET)
    public String registerPage() {
        return "user/register";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String register(@RequestParam("name") String name, @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           Map<String, Object> model, RedirectAttributes redirectAttributes) {
        User user = new User();
        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            userRepository.save(user);
            redirectAttributes.addAttribute("registeredName", name);
            redirectAttributes.addAttribute("registeredEmail", email);
            return "redirect:/user/success";
        } else
            model.put("errorMsg", "Preencha todos os campos!");
            return "user/register";
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String listPage(Map<String, Object> model) {
        Iterable<User> userList = userRepository.findAll();
        model.put("userList", userList);
        return "user/list";
    }

}
