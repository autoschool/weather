FROM maven
MAINTAINER Merkushev Kirill (github:lanwen)

ENV WEATHER_BASE_DIR /weather
RUN mkdir ${WEATHER_BASE_DIR} 
    
ADD . ${WEATHER_BASE_DIR}
WORKDIR ${WEATHER_BASE_DIR}
RUN ["mvn", "clean", "compile"]

EXPOSE 8080

CMD ["mvn", "jetty:run"]

