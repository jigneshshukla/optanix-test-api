FROM maven:3.6.0-jdk-8 AS build
WORKDIR /workdir/
COPY pom.xml .
COPY src/ src/
RUN mvn -DforkCount=0 package

FROM openjdk:8-jdk-alpine
COPY --from=build /workdir/target/*.jar app.jar

# Create a non-root user and group
RUN addgroup -g 1000 -S appgroup && adduser -u 1000 -S appuser -G appgroup

# Set image to run as non-root user just created in previous step
USER 1000

CMD ["java","-jar","app.jar"]
