FROM java:8

LABEL maintainer=“tkarabatsis@di.uoa.gr”

WORKDIR /app

COPY target/enabling-services-0.0.1-SNAPSHOT.jar /app/enablingServices.jar

ENTRYPOINT ["java","-jar","enablingServices.jar"]