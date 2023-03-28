FROM openjdk:17-oracle
COPY ./build/libs/*release.jar /
CMD ["java", "-cp", "/*release.jar"]