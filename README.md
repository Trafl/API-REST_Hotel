# API-REST_Hotel
System for hotel registration, room rental, and customer registration.

## Technologies used
This project was made with the following technologies:
- Java 17
- Spring Boot 3.0
- Spring Data
- Spring Email
- Spring Hateos
- SpringDoc
- OpenApi
- Lombok
- FlyWay
- ScheduledTasks
- JasperReports
- ModelMapper
- AWS S3
- Docker
- Docker compose
- MySQL
- Docker image of MySQL 8.0 database
- Docker image of SDK Eclipse-temurin:17-jre-alpine
- Docker image of Nginx reverse proxy

## Requirements
To run this project, you need to have the following programs installed on your machine:
- Docker
- Docker Compose

## How to run this project
Go to the project folder where the `docker-compose.yaml` file is located and then use the following command to run the project.

`
docker-compose up
`

To run the application with more than one instance, use:


`
docker-compose up --scale hotelo-api=2
`

In this case, the port that will run is 80, the port defined for Nginx.

To stop the execution, use the command:

`
Ctrl + C
`

or, in another terminal open in the same path:
```
docker-compose stop
```

`docker-compose up` will create the containers specified in the `docker-compose.yaml` file and initialize them.

After the containers are created, use a program like `Postman` or `Insomnia` to make HTTP requests.
Once you have Insomnia or Postman open, go to the URL [http://localhost:8080/](http://localhost:8080/) to interact with the API root endpoint.

## Detailed API Endpoints Documentation
The application was documented using the OpenApi specification.
After starting, go to the link -> [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

## Example Images
- Adding a new user

![adding a customer](https://github.com/Trafl/assets/blob/main/adicionandoCliente.jpg)

- Reading a user

![Reading a customer](https://github.com/Trafl/assets/blob/main/getCliente.jpg)

- Adding a hotel

![adding a hotel](https://github.com/Trafl/assets/blob/main/postHotel.jpg)

- Reading a hotel

![Reading a hotel](https://github.com/Trafl/assets/blob/main/exemplo%20hoteis.jpg)

- Reservation model in PDF

![](https://github.com/Trafl/assets/blob/main/ModeloPdf.jpg)

- Reservation in Json

![Screenshot from 2022-08-29 18-02-26](https://github.com/Trafl/assets/blob/main/getRent.jpg)

