FROM openjdk:11
VOLUME /tmp
EXPOSE 8888
ADD ./target/products-service-1.0.0.jar products-service-1.0.0.jar
ENTRYPOINT ["java","-jar","/products-service-1.0.0.jar"]