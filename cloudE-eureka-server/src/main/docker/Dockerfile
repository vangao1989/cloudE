FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD cloudE-eureka-server-1.0.0.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
