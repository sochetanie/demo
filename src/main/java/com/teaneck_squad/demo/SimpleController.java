package com.teaneck_squad.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimpleController {

    @GetMapping("/hello")
    public String hello() {
        return "hello, world";
    }

    @RequestMapping(value = "/goodbye", method = RequestMethod.GET)
    public String goodbye() {
        return "goodbye, night sky";
    }

    @GetMapping("/hello/{name}")
    public String helloYou(@PathVariable(value="name") String name) {
        return "hello " + name;
    }

    @PostMapping("/hello")
    public String helloName(ModelMap mm, @RequestParam String name) {
        return "hello " + name;
    }

    @PostMapping("/hello-dynamic")
    public ResponseEntity<?> helloDynamic(ModelMap mm, @RequestParam String name) {
        mm.put("greeting", "hello " + name);
        return new ResponseEntity<>(mm, HttpStatus.OK);
    }
}
