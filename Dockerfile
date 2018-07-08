
FROM openjdk:10

WORKDIR /app

ADD . /app
EXPOSE 9999

CMD ["/app/gradlew", "build"]
CMD ["/app/gradlew", "installDist"]
CMD ["/app/build/install/nextid/bin/nextid"]
