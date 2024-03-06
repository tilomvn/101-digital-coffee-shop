FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
COPY target/*.jar coffeeshop.jar
ENTRYPOINT ["java","-jar","/coffeeshop.jar"]
