FROM maven:latest

WORKDIR /app

COPY pom.xml .

RUN mvn clean package -DskipTests

COPY . .

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]