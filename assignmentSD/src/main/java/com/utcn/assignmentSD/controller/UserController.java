package com.utcn.assignmentSD.controller;

import com.utcn.assignmentSD.model.Question;
import com.utcn.assignmentSD.model.User;
import com.utcn.assignmentSD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    @ResponseBody
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUser")
    @ResponseBody
    public User getUser(@RequestParam(name = "id") Integer id) {
        return userService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam(name = "id") Integer id) {
        return userService.deleteUser(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/saveUser")
    @ResponseBody
    public User saveUser(@RequestBody  User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateUser")
    @ResponseBody
    public User updateUser(@RequestParam(name = "id") Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/seeUserQ")
    @ResponseBody
    public Set<Question> seeQuestions(@RequestParam(name = "id") Integer id) {
        return  userService.seeQuestions(id);
    }

}

