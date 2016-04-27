FROM java
MAINTAINER Merkushev Kirill (github:lanwen)

ENV WEATHER_BASE_DIR /weather
RUN mkdir ${WEATHER_BASE_DIR}

ADD target/export ${WEATHER_BASE_DIR}
WORKDIR ${WEATHER_BASE_DIR}

VOLUME /etc/weather/server
EXPOSE 8080

CMD ["java", "-Xbootclasspath/a:/etc/weather/server", "-jar", "runner.jar", "server.war"]