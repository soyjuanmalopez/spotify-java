FROM 660656973203.dkr.ecr.eu-west-1.amazonaws.com/openjdk:11-jre-buster
VOLUME /tmp
ADD ./target/*.jar /opt/app/app.jar
RUN sh -c 'touch /opt/app/app.jar'
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS $MONITORING -jar /opt/app/app.jar"]
