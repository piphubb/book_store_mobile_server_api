FROM openjdk:11
EXPOSE 2030
ADD target/post-free-server-api.war post-free-server-api.jar
ENTRYPOINT ["java","-jar","/post-free-server-api.jar"]