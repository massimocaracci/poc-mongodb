# Getting Started

### Docker Compose for developement purpose
For developement purpose, if you don't have MongoDb installed run the commands:
``` 
docker-compose up -d
```
- Create Database `<<database_name>>`
- Create User `<<username>>`
- Create collection `reservations`


### Robo 3T
You can use Robo 3T to create the database and user.
- https://robomongo.org


### Mongo Express
You can use Mongo Express to access your local or existing mongo DB. 
- visit http://localhost:8081, or http://host-ip:8081 (as appropriate).

### Note

Make sure that the information that you define in the application.yml are correct
```
spring:
  data:
    mongodb:
      host: localhost
      port: 27017
      database: <<database_name>>
      username: <<username>>
      password: <<password>>
```
