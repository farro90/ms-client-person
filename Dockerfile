FROM openjdk:11
VOLUME /tmp
EXPOSE 8101
ADD ./target/ws-client-person-0.0.1-SNAPSHOT.jar ws-client-person.jar
ENTRYPOINT ["java","-jar","ws-client-person.jar"]