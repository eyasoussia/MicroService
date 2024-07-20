FROM openjdk:17-jdk-slim
EXPOSE 9010
ADD target/*.jar gateway.jar
ENTRYPOINT ["java","-jar","gateway.jar"]
