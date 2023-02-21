package net.wushilin.webhookdebug

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebhookDebugApplication

fun main(args: Array<String>) {
	runApplication<WebhookDebugApplication>(*args)
}
