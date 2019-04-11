# demo
demo of a simple rest server built in java/spring

##Instructions
 - Import this project into IntelliJ as a gradle project
 - There are 3 main files worth looking at to gain some insight into how this works:
    + **build.gradle** - defines what packages our project needs in order to run & make use of the Spring Boot framework
    + **DemoApplication.java** - Our "main" application class that is actually runnable and initializes Spring
    + **SimpleController.java** - a controller class that defines all of the accessible routes in this application
 - Assuming the project was imported successfully, the DemoApplication class should be runnable by your IDE.
 - Start your application, and send a GET request to `http://localhost:8080/hello` using Postman, or from the command line `curl http://localhost:8080`
 - Take some time to look through each of these files, and read the comments inside!
 
