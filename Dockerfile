FROM openjdk:11

COPY target/FamilyApp-*.jar /FamilyApp.jar

CMD ["java" , "-jar" , "/FamilyApp.jar" ]

# FROM maven
# RUN mkdir /FamilyApp
# WORKDIR FamilyApp
# COPY target/FamilyApp-*.jar /FamilyApp.jar
# RUN mvn clean install
# CMD "mvn" "exec:java"