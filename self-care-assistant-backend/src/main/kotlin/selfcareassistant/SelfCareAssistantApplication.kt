package selfcareassistant

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SelfCareAssistantApplication

fun main(args: Array<String>) {
    runApplication<SelfCareAssistantApplication>(*args)
}
