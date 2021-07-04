FROM openjdk:8-jre-alpine

ENV WIREMOCK_VERSION 2.25.1

# grab wiremock standalone jar
RUN mkdir -p /var/wiremock/lib/ \
  && wget https://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-jre8-standalone/$WIREMOCK_VERSION/wiremock-jre8-standalone-$WIREMOCK_VERSION.jar \
    -O /var/wiremock/lib/wiremock-jre8-standalone.jar

WORKDIR /home/wiremock

VOLUME /home/wiremock/mappings
EXPOSE 9090

CMD java -jar /var/wiremock/lib/wiremock-jre8-standalone.jar --port 9090 --verbose --local-response-templating
