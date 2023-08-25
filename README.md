# API_Hotel
Um sistema para cadastro de hoteis e seus respectivos clientes.

## Tecnologias usadas
Este projeto foi feito com as seguintes tecnologias:
- Java 17
- Spring Boot 3.0
- Spring Data
- Spring Email
- Spring Hateos
- SpringDoc
- OpenApi
- Lombook
- FlyWay
- ScheduledTasks
- JasperReports
- ModelMapper
- AWS S3
- Docker
- Docker compose
- MySQL
- Imagem docker do banco de dados MySQL 8.0
- Imagem docker do SDK Eclipse-temurin:17-jre-alpine
- Imagem docker do proxy reverso Nginx

## Requisitos
Para rodar este projeto, é necessário ter instalados na sua máquina os seguintes programas
- Docker
- Docker Compose

## Como executar este projeto
Entre na pasta do projeto, onde está o arquivo `docker-compose.yaml` e então use o seguinte comando para executar o projeto.

`
docker-compose up
`

Para subir a aplicacão com mais de uma instancia utilize:

`
docker-compose up --scale hotelo-api=2
`
, nesse caso a porta que vai rodar e a 80 a porta definida para o Nginx

Para parar a excução, utilize o comando:
`
Ctrl + C
`
ou, em outro terminal aberto no mesmo caminho:
```
docker-compose stop
```
`docker-compose up` criará os containers especificados no arquivo `docker-compose.yaml` e os inicializará.

Após os containers serem criados, use um programa como `Postman` ou `Insomnia` para fazer as requisições http.
Uma vez com o Insomnia ou Postman aberto, vá até a url [http://localhost:8080/](http://localhost:8080/) para interagir com o rootpoint da api rest.

## Documentação detalhada dos EndPoins da Aplicação
A aplicação foi documentada utilizando a especificação da OpenApi
Apos o iniciar va ao link  -> [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

## Imagens de Exemplo
- Adicionando um usuário novo

![adicionando um cliente](https://github.com/Trafl/assets/blob/main/adicionandoCliente.jpg)

- Lendo um usuário 

![Lendo um cliente](https://github.com/Trafl/assets/blob/main/getCliente.jpg)

- Adicionando um hotel

![procurando](https://github.com/Trafl/assets/blob/main/postHotel.jpg)

- Lendo um hotel

![Lendo um hotel](https://github.com/Trafl/assets/blob/main/exemplo%20hoteis.jpg)

- Modelo da reserva em PDF

![](https://github.com/Trafl/assets/blob/main/ModeloPdf.jpg)

- Reserva em Json

![Captura de tela de 2022-08-29 18-02-26](https://github.com/Trafl/assets/blob/main/getRent.jpg)
