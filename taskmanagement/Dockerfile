FROM openjdk:17-jdk-slim
EXPOSE 8099
ADD target/*.jar taskmanagement.jar
ENTRYPOINT ["java","-jar","taskmanagement.jar"]
