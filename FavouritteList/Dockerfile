FROM openjdk:17
EXPOSE 8086
ADD target/wishlist-docker.jar wishlist-docker.jar
ENTRYPOINT ["java", "-jar", "/wishlist-docker.jar"]