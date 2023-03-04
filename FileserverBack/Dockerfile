# Build stage
FROM gradle:7.5.0-alpine AS build
WORKDIR /home/gradle/src
COPY . /home/gradle/src
RUN  gradle build -x test --no-daemon   

#Package stage
FROM openjdk:17.0.1-jdk-slim
#Health check tools
RUN apt-get update && apt-get install -y \
curl
RUN apt-get update && apt-get install -y \
jq
CMD /bin/bash
#End health check tools
ARG JAR_NAME="FileserverBack-1.0.0"
WORKDIR /home/gradle/src
EXPOSE ${HTTP_PORT}
COPY --from=build /home/gradle/src/build/libs/${JAR_NAME}.jar ./file-server-back.jar
ENTRYPOINT ["java","-jar", "./file-server-back.jar"]
