FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD cloudE-config-server-1.0.0.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8888
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
