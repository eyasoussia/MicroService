FROM  openjdk:17-jdk-slim
EXPOSE 8888
ADD target/config*.jar config.jar
ENTRYPOINT ["java", "-jar", "config.jar"]