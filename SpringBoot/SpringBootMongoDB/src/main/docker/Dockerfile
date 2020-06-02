FROM java
VOLUME [ "/tmp" ]
ADD sample-rest-mongo-1.0.jar app.jar
RUN sh -c 'touch /app.jar'

ENV JAVA_OPTS=""

ENV MONGO_HOST="mongodb"
ENV MONGO_PORT="27017"
ENV MONGO_DB="ecommerce"
ENV APP_PORT="8080"

EXPOSE ${APP_PORT}

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]