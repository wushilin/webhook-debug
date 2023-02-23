#!/bin/sh
echo "You must make sure your java version is 17+"
echo "If you have set JAVA_HOME env variable, it must point to a JDK 17 home"
./gradlew clean bootJar
