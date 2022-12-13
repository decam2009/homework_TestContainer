FROM openjdk
EXPOSE 8081
ADD target/springboot_Conditional-0.0.1-SNAPSHOT.jar prod.jar
ENTRYPOINT ["java", "-jar", "prod.jar"]