FROM openjdk:11

COPY target/FamilyApp-*.jar app.jar

CMD ["java" , "-jar" , "app.jar" ]
