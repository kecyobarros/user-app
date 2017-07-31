# user-app

Application responsible for provide user information

# Frameworks used:
- Spring (Boot, Data, actuator)
- Lombok
- Swagger
- Mockito
- Jacoco (Coverage of tests)

# To Execute
A compose file has been made available to create the mongoDB container.

Archive: **infra/compose/docker-compose.yml**

1. Run the file with the following **docker-compose up -d** command inside the infra/compose.
2. Run **mvn clean install** in the project root folder.
3. Run the command **java -jar target/user-app.jar**
4. If you do not have the **JAVA_HOME** variable in your path then run the class **UserAppApplication.java** for your ide.

# Endpoint documentation
http://localhost:8080/api/swagger-ui.html
