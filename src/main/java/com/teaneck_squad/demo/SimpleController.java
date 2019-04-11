package com.teaneck_squad.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/*
    When our spring application starts, it spawns up an apache Tomcat server and finds classes marked with
    a @Controller or @RestController annotation to decide how to route requests coming into our web server.
    Any class or method marked with a @*Mapping annotation will generate an endpoint on our web server that can be reached
    via the specified HTTP verb.
    @GetMapping endpoints are reachable by sending a GET request to the specified endpoint
    @PostMapping - POST
    @PutMapping - PUT
    @PatchMapping - PATCH
    @DeleteMapping - DELETE

    All of the above mapping annotations are simply decorators on top of the @RequestMapping annotation, which allows
    us to specify the route AND http verb in its declaration
 */

@RestController
public class SimpleController {

    /*
       This is an endpoint using the @GetMapping annotation which tells the hello() method to run
       when a GET request comes in to "/hello". The spring framework will take the return value from hello() and
       automatically package it up into a response object which it then sends back to the client.
   */
    @GetMapping("/hello")
    public String hello() {
        return "hello, world";
    }

    /*
        Here is another GET endpoint, this time using the @RequestMapping annotation to run the goodbye() method when
        a GET request comes in to "/goodbye". Notice that both annotations achieve essentially the same goal of creating
        a GET endpoint for us, but with RequestMapping, we have to specify which RequestMethod we want, where as getMapping
        is automatically (and only ever going to be) a GET request
     */
    @RequestMapping(value = "/goodbye", method = RequestMethod.GET)
    public String goodbye() {
        return "goodbye, night sky";
    }

    /*
        Here, we have a more dynamic GET endpoint that allows the user to provide a value in the url to dynamically alter
        the content that will be sent back.

        Start your server, then using postman,
        try sending GET requests to http://localhost:8080/hello/jeff
        and http://localhost:8080/hello/kay

        and notice how your response changes depending on the name you put into the path! This is known as a path variable,
        and defined using the @PathVariable annotation
     */
    @GetMapping("/hello/{somename}")
    public String helloYou(@PathVariable(value="somename") String name) {
        return "hello " + name;
    }

    /*
        Below is another endpoint defined at "/hello", this time accepting POST requests. This will respond in the same manner
        as the GET endpoint defined directly above, however, the structure in which we send a name with our request is slightly different.
        In order to send a name to this endpoint, we need to put it inside of a JSON object under the "name" property.
        It should look something like this:
        {
            "name" : "Bob"
        }
     */
    @PostMapping("/hello")
    public String helloName(@RequestParam String name) {
        return "hello " + name;
    }

    /*
        All of the previous endpoints we've seen return the same data type, a String. If we want to return a different
        response from our endpoint depending on what the user has given us, we can instead have our method return a generic
        ResponseEntity<?> type. This is particularly useful if the request is invalid or improperly structured, and
        we want to send back custom messages depending on the issue. With the ResponseEntity object, we can
        define what status code to send back as well.

        In order to build the response object to send back, we can dynamically create an object using the ModelMap type.
        Think of the ModelMap as a builder that let's us add in any amount of property-value pairs to an object using
        its own .put() method. The ModelMap.put() method is straightforward - it takes a key as its first argument
        (often times this will simply be a string), and a value as a second argument (this can be whatever you want!
        Primitive values or nested objects).

        Once the response object has been formed using the ModelMap, it can be passed in as the first argument to a
        new ResponseEntity object, which Spring will then transform into a response to send back to the client.

        Try sending POST requests with two different names "Rick" and "Spongebob", and notice the different responses you get back!
     */
    @PostMapping("/hello-dynamic")
    public ResponseEntity<?> helloDynamic(ModelMap mm, @RequestParam String name) {
        if (name.length() > 5){
            mm.put("message", "your name is too long!");
            return new ResponseEntity<>(mm, HttpStatus.UNPROCESSABLE_ENTITY);
        }else {
            mm.put("greeting", "hello " + name);
        }
        return new ResponseEntity<>(mm, HttpStatus.OK);
    }

    /*
        The ResponseEntity<?> data type is pretty flexible with regards to what it can package up and send to the client.
        Notice here that it can take an object that has been built using a ModelMap, OR a plain old java object
        (in this case, a Person object), and Spring (in conjunction with the Jackson library), knows how to automatically
        convert any of it into a JSON response for you!
     */
    @PostMapping("/person-dynamic")
    public ResponseEntity<?> goodbyeDynamic(ModelMap mm, @RequestParam String name) {
        if (name.length() > 5){
            mm.put("message", "your name is too long!");
            return new ResponseEntity<>(mm, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Person p = new Person(name, 999);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    private class Person {
        private String name;
        private int age;
        Person(String n, int a) {
          this.name = n;
          this.age = a;
        }

        public String getName() {
            return name;
        }

        public int getAge(){
            return age;
        }
    }
}
