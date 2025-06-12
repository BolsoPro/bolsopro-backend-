FROM maven:3.9.6-eclipse-temurin-21

WORKDIR /usr/src/app

COPY . .

RUN mvn clean install 

EXPOSE 8080

CMD ["sh", "-c", "java -jar target/*.jar"]