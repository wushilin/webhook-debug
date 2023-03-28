FROM docker.io/openjdk:17-oracle
COPY ./build/libs/webhook-debug-1.0.3-release.jar /
CMD ["java", "-jar", "/webhook-debug-1.0.3-release.jar"]
