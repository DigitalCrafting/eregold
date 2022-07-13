# eregold - 05-microservices-plus-kafka-transactions-system

This version of the **Eregold** application is the most complicated one. The difference from 
[Version 04](https://github.com/DigitalCrafting/eregold/tree/master/04-web-app-plus-microservice-middleware) is that the transactions system will be expanded to use Kafka pub-sub model, to properly check if the transaction can be done and that the account balance is correctly updated.

---
## How to run
### Requirements
- Java 11
- Maven
- Docker
#### Optional
- NodeJS 14.17.5
- Npm 6.14.14

### Steps
#### Linux
1. Run **build.sh** script
2. Run **docker-compose up**
4. Run **migrate_db.sh** script
5. Application will be available on **localhost:4200**

#### Windows
1. Run **mvn clean install -Pprod** in backend directory
2. Run **mvn clean install** in in each (accounts, api-gateway, customers, transactions) microservice directory
3. Run **docker-compose up** in this directory
4. Run **mvn flyway:migrate** in each (accounts, customers, transactions, users) database directory
5. Application will be available on **localhost:4200**

## Architecture TODO update with Kafka

The architecture is as follows:

![Diagram](./assets/diagram.png)

- User Facing System
  - Backend 
    - is stateless - the user session is stored Redis,
    - connects to Users Database,
    - connects to Core Banking System in order to access customer data like accounts, and transactions,
  - Frontend
    - is bundled with the jar, and not run standalone,
    - connects directly to backend
  - Users Database
    - stores user-specific data (login, password)
- Core Banking System
  - API Gateway
    - single point of entry to Core System,
    - also acts as a service discovery and load balancer
  - Accounts
    - Accounts Service
      - microservice handling accounts related operations
    - Accounts DB
      - contains accounts related tables
  - Transactions
    - Transactions Service
      - microservice handling transactions related operations
    - Transactions DB
      - contains transactions related tables
  - Customers
    - Customers Service
      - microservice handling customers related operations
    - Customers DB
      - contains customers related tables