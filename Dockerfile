FROM tomcat
MAINTAINER ubuntu 
ENV JAVA_OPTS="${JAVA_OPTS} -Dspring.profiles.active=development"

COPY target/calories-tracker.war /usr/local/tomcat/webapps/ROOT.war

ENV TZ=America/New_York
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
