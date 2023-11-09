# eregold

Behold! The mighty dwarves of The Kingdom of Erebor now provide banking services!

## Goal

Goal of this project is to create different versions of the same application, **Eregold**. Each version will increase in complexity, from simple frontend -> backend -> db application, to a microservices one, which will hopefully help new developers understand all the layers of modern web applications.

## CLI

Project comes with a CLI, which can be used to run docker-compose commands for each project. From top level you can use it like:
`./eregold -b 01 -f angular up`

You can see possible flags with:
`./eregold help`

## Versions
### [Basic Web Application](https://github.com/DigitalCrafting/eregold/tree/master/01-basic-web-app)
This is the simplest, one jar web application and database. This kind of application is not really suitable for banking anymore, but it should serve as a starting point for everyone interested in how web application communicates from top to bottom.

### [Stateless Web Application](https://github.com/DigitalCrafting/eregold/tree/master/02-stateless-web-app)
This one is almost the same as [Basic Web Application](https://github.com/DigitalCrafting/eregold/tree/master/02-stateless-web-app), except the session is stored in Redis, which enables the backend to be stopped and started without having to login again.

### [Web App with Monolithic Middleware](https://github.com/DigitalCrafting/eregold/tree/master/03-web-app-plus-monolith-middleware)
In this version, the backend no longer connects directly to the full database. Instead, backend has access only to Users Database, which only stores application user data, while the core banking functionality is behind another system called 'Middleware'. The purpose of middleware is to provide common API for accessing the database, for all different banking applications, like User Application (eregold), Call Center Application, CRM etc. 

### [Web App with Microservices Middleware](https://github.com/DigitalCrafting/eregold/tree/master/04-web-app-plus-microservice-middleware)
In this version, the middleware is no longer a monolithic application, but a microservices system, each microservice having it's own database, which makes sense since there will way more transactions than customers, which also will need more servers to handle.

Not every single feature really needs a separate microservice, for example for the most part, customers and accounts could be bundled together as they are naturally coupled.

### [Middleware with Kafka Transactions System](https://github.com/DigitalCrafting/eregold/tree/master/05-microservices-plus-kafka-transactions-system)

This version of the project expands the middleware to use Kafka messaging for communication with transactions system. 
Also, the transactions logic is in the correct place - fully in the middleware, whereas in the  [Web App with Microservices Middleware](https://github.com/DigitalCrafting/eregold/tree/master/04-web-app-plus-microservice-middleware)
the logic wasn't really properly placed.

## Functionality

**Eregold** will be very simplified banking application, where user can:
- register
- login
- request a new account
- make a transfer between own accounts
- make a deposit

## Tech stack

### Frontend
#### React
- React 18
- Vite

#### Angular
- Angular 12
- Webpack 5

### Backend
- Java 11
- Spring BOOT
- Spring Cloud
- Maven
- MyBatis
- Flyway
- Kafka

### Database
- PostgreSQL
- Redis

### Other
- Docker
- Docker-compose

