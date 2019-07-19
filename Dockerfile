FROM tomcat:9.0
LABEL maintainer="tkarabatsis@di.uoa.gr"
COPY target/enabling-services-0.0.1-SNAPSHOT	 /usr/local/tomcat/webapps/enabling-services-0.0.1-SNAPSHOT