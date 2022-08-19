# UserManagementAndAuthenticationApp

### Steps to run the database(MongoDB)

1. Goto the root directory ./UserManagementAndAuthenticationApp
2. run command, docker-compose up -d --build (Make sure you have docker installed)
3. This is to bring up MongoDB cluster at port 27017

### Steps to run the application

1. Make sure you have Java 17, maven installed.
2. Goto the root directory ./UserManagementAndAuthenticationApp
3. Run, mvn clean package (It will build and package the application is necessary jars required to run the application)
4. Goto target folder.
4. Run the application using command, java -jar UserManagementAndAuthenticationApp-0.0.1-SNAPSHOT.jar
5. Open Postman, use post request, http://localhost:8080/user/add to add user
6. Use http://localhost:8080/user/name/{name} to get specific user
7. Use http://localhost:8080/user/all to get all users
8. Use http://localhost:8080/user/name/{name}/delete to delete user with specific name
9. Use http://localhost:8080/user/update to update the user
