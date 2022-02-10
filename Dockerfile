FROM openjdk:8
ADD target/newsletter-subscription-service-0.0.1-SNAPSHOT.jar newsletter-subscription-service-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","newsletter-subscription-service-0.0.1-SNAPSHOT.jar"]docker