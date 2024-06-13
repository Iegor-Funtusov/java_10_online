#FROM openjdk:21

#COPY target/docker_demo.jar docker_demo.jar
#ENTRYPOINT ["java", "-jar", "docker_demo.jar"]

#FROM maven:3.8.3-openjdk:21
FROM maven:3.9.7-amazoncorretto-21-debian
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package
ENTRYPOINT ["java", "-jar", "/usr/src/app/target/docker_demo.jar"]
