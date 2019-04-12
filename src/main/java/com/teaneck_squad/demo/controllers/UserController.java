package com.teaneck_squad.demo.controllers;


import com.teaneck_squad.demo.models.User;
import com.teaneck_squad.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        ModelMap mm = new ModelMap();
        if(userService.createUser(user)){
            mm.put("message", "user succesfully created");
            return new ResponseEntity<>(mm, HttpStatus.OK);
        }
        else {
            mm.put("message", "unable to add this user");
            mm.put("user", user);
            return new ResponseEntity<>(mm, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") long id) {
        try {
            User u = userService.getUser(id);
            return new ResponseEntity<>(u, HttpStatus.OK);
        }catch(Exception e) {
            ModelMap mm = new ModelMap();
            mm.put("errorMessage", "user not found");
            return new ResponseEntity<>(mm, HttpStatus.NOT_FOUND);
        }

    }

}
