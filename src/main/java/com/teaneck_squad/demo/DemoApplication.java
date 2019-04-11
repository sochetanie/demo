package com.teaneck_squad.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	This is where the magic happens! Using the @SpringBootApplication on our DemoApplication class, and calling
	SpringApplication.run(DemoApplication.class, args) in our main method, the spring framework will scan all of
	our java files in the project and automatically configure a webserver for us! the annotation provides an automatic
	configuration for us, so we don't have to worry about it ourselves. By default, our server will start listening for
	HTTP requests on port 8080.
 */

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
