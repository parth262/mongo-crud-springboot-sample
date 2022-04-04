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

# Docker build and run Steps

1. Build jar with gradle: `./gradlew clean build -x test`

2. Build Docker image: `docker build -t mongo-api .`

3. Create Docker network: `docker network create api-network`

4. Run mongo docker image with network: `docker run --name docker_mongo -d -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin --network api-network mongo`

5. Run api docker image with network: `docker run -p 8080:8080 -itd --network api-network mongo-api`

# Docker push steps

1. Create repository on [Docker hub](https://hub.docker.com/)

2. Tag local image with repository name: `docker tag <local image> <repository image>`

3. Push repository image: `docker push <repository image>`