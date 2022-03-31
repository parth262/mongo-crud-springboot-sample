FROM openjdk:11

WORKDIR /app

COPY ./data ./data

ENV PORT 8080
ENV ENVIRONMENT dev
ENV MONGO_SERVER docker_mongo
ENV MONGO_USER admin
ENV MONGO_PASS admin

COPY build/libs/mongo-api-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

CMD java -jar app.jar