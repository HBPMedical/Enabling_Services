# Enabling_Services
Enabling Services for the MIP front end.

## Create Image

	mvn package
	docker build -t hbpmedical/enabling_services .
	


## Deploy:

### Create local docker network

	docker network create --subnet=172.18.0.0/16 enabling_services

### Run Postgres
	docker run -d -e POSTGRES_PASSWORD=password --name postgres --network=enabling_services --ip 172.18.0.100 -p 5432:5432 postgres:11-alpine

### Setup the postgres schema

	psql --host 127.0.0.1 --port 5432 --username postgres -f /path/to/the/postgresDatabaseSchema.sql



### Run the Spring Boot API
	
	docker run -d --name enabling_services --network=enabling_services hbpmip/enabling_services:latest
