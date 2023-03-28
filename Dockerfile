FROM docker.io/openjdk:17-oracle
COPY ./build/libs/*release.jar /
CMD ["java", "-cp", "/*release.jar", "net.wushilin.webhookdebug.WebhookDebugApplicationKt"]
