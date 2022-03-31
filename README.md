# Properties:
## Mongo 
- user: admin
- password: admin
- docker container name: docker_mongo

## API
- environment: dev
- port: 8080
- docker image name: mongo-api

## Other
- docker network name: api-network

# Steps

1. Build jar with gradle: `./gradlew clean build -x test`

2. Build Docker image: `docker build -t mongo-api .`

3. Create Docker network: `docker network create api-network`

4. Run mongo docker image with network: `docker run --name docker_mongo -d -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin --network api-network mongo`

5. Run api docker image with network: `docker run -p 8080:8080 -itd --network api-network mongo-api`