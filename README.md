# trading-view

## background
This is a Spring Boot application that I am using to get familiar with Spring and RESTful API's. It uses a postgres database with the timescaleDB extension.

There is a react frontend that uses chartjs to display stock data.

## setup
You will need the following file: src/main/resources/application.properties. I have ignored mine as I store my local passwords here.
```
spring.datasource.url=jdbc:postgresql://host:port/dbname
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver
```