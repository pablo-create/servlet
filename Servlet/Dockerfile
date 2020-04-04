FROM tomcat:8.5.53-jdk11-openjdk

EXPOSE 8080
COPY ./build/libs/Servlet.war /usr/local/tomcat/webapps/Servlet.war
CMD ["catalina.sh", "run"]