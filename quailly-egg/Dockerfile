FROM adoptopenjdk/openjdk11:alpine-jre

COPY ./build/libs/quailly-egg.jar quailly-egg.jar
CMD ["java", "-Xmx128m", "-jar", "quailly-egg.jar"]
