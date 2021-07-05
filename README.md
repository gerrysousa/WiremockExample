# WiremockExample

Simple project to demonstrate wiremock utilization.

This project contains 2 microservices.
Service 1 - Schools
- Endpoints
    - POST http://localhost:4000/schools (To insert a new school on Database)
    - PUT http://localhost:4000/schools/{id} (To update a teacher by given ID)
    - DELETE http://localhost:4000/schools/{id} (To delete a teacher by given ID)
    - GET http://localhost:4000/schools/{id} (To list a teacher by given ID)
    - GET http://localhost:4000/schools  (To list all schools)
    - GET http://localhost:4000/schools/findByName/{name} (To list schools by given name)


Service 2 - Teacher
- Endpoints
    - POST http://localhost:4000/teachers (To insert a new teacher on Database)
    - PUT http://localhost:4000/teachers/{id} (To update a teacher by given ID)
    - DELETE http://localhost:4000/teachers/{id} (To delete a teacher by given ID)
    - GET http://localhost:4000/teachers/{id} (To list a teacher by given ID)
    - GET http://localhost:4000/teachers  (To list all teachers)
    - GET http://localhost:4000/teachers/findByName/{name} (To list teachers by given name)
    - GET http://localhost:4000/teachers/{id}/schools (To list all schools from a teacher by given teacherId)
    
  
----
 Note:
- The endpoint "/teachers/{id}/schools" of teacher-service depens on school-services to retrieve schools information. 
Therefore, if the school-service is not available, we can use the wiremock to simulate school-service responses.

----

- How to run?

School-service:
- Access the "school-service" folder, and run the comand below to start a docker container with MySQL.

```$docker-compose up ```

- Inside your IDE, run the SchoolServiceApplication, the microservice will be available on port 4000, you can change the port number by application.properties  

-------

Teacher-service:
- There are two ways to run this service.

If you don't need the Wiremock.
Access the "teacher-service" folder, and run the comand below to start a docker container with MySQL.

```$docker-compose up ```

- Inside your IDE, run the SchoolServiceApplication, the microservice will be  available on port 4000, you can change the port number by application.properties  

If you need the Wiremock simulation.
Access the "teacher-service" folder, and run the comand below to start a docker container with MySQL.

```$docker-compose -f docker-compose-wiremock.yml up```

The wiremock will be available on port 9090.

