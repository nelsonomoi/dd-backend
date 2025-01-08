
# Dd backend

Follow the following instruction to run  dd backend locally.


## Requirements 
1. Java v23 - https://www.oracle.com/ke/java/technologies/downloads/
2. Postgres DB - https://www.postgresql.org/download/


## Database setting
Create a postgres database with the following credentials
```
    dbname = ddfinance
    dbpassword = postgres
    dbuser = postgres
    dbport = 5432
    dbhost = localhost
```



## Installation
1. Clone the repo from https://github.com/nelsonomoi/dd-backend.git
```
    git clone https://github.com/nelsonomoi/dd-backend.git
```

2. Navigate to the directory and run
```
    ./mvnw spring-boot:run
```

Backend service is now running on port 8080


