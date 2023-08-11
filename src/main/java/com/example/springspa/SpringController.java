package com.example.springspa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class SpringController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userRepository.save(user);
        return "saved";
    }

    @RequestMapping(path = "/changeEmail", method = RequestMethod.PUT)
    @ResponseBody
    public String changeEmail(@RequestParam String oldEmail, @RequestParam String newEmail){
        User user = userRepository.findByEmail(oldEmail);
        user.setEmail(newEmail);
        userRepository.save(user);
        return "email changed";
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }
    
    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@RequestParam String email){
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
        return "User deleted";
    }
}
