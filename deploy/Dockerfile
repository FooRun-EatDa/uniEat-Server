FROM adoptopenjdk/openjdk15
VOLUME /tmp
COPY build/libs/foorun-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Duser.timezone='Asia/Seoul'", "-Dspring.profiles.active=production", "-Xmx2048m", "-jar","/app.jar"]
