FROM openjdk:11

COPY target/FamilyApp-*.jar /FamilyApp.jar

CMD ["java" , "-jar" , "/FamilyApp.jar" ]
