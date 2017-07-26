FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD cloudE-eureka-server-1.0.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
