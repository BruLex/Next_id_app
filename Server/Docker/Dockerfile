
FROM openjdk:10

WORKDIR /app

ADD . /app
EXPOSE 9999
RUN cd /tmp && wget https://services.gradle.org/distributions/gradle-4.8.1-bin.zip
RUN mkdir -p /opt/gradle
RUN unzip -d /opt/gradle /tmp/gradle-4.8.1-bin.zip
RUN cd /app/ && /opt/gradle/gradle-4.8.1/bin/gradle wrapper
RUN ["/app/gradlew", "build"]
RUN ["/app/gradlew", "installDist"]
CMD ["/app/build/install/nextid/bin/nextid"]
