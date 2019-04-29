# springboot-library-microservice

Prerequisite ->
----
Postgres should be installed on your system.


Steps->
----
1) Clone this repository from master.

2) Import this project in eclipse as an existing maven project.

3) Open "application.properties" file and change the following entries (give the username and password of your postgres database) ->

      spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
      
      spring.datasource.username=postgres
      
      spring.datasource.password=postgres
      

3) Build the project using "clean install" , this will download all the dependencies.

4) In eclipse go to "com.imtf.library.App.java" and run the main method as a "Java Application".

5) This application runs on port 8084.

6) Type http://localhost:8084/hello in the browser , you will get the following message indicating that the backend service is up and running.
Response -> "Hi, Microservice is Up and Running"

7) You can test this microservice end to end using this angular app -> https://github.com/maluskarhrishikesh/angular-library-app
