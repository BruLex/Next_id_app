
FROM openjdk:10

WORKDIR /app

ADD . /app
EXPOSE 9999
RUN ["/app/gradlew", "build"]
RUN ["/app/gradlew", "installDist"]
CMD ["/app/build/install/nextid/bin/nextid"]
