# eregold - 01 Basic web app

This version of the **Eregold** application is a simple, one jar web application and database, which means:

- frontend will be served from within the final jar when build for prod,
- backend connects directly to the single database,
- there is single database that stores everything.

This kind of application is not really suitable for banking anymore, but it should serve as a starting point for
everyone interested in how web application communicates from top to bottom.

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
1. Run **mvn clean install -Pprod** in backend directory
2. Run **docker-compose up** in this directory
3. Application will be available on **localhost:4200**