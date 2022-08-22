FROM openjdk:11
WORKDIR /app
ADD build/libs/*.jar image.jar

RUN bash -c 'touch /image.jar'
ENTRYPOINT ["java","-jar","image.jar"]