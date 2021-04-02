package selfcareassistant

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.sql.Date
import java.sql.Time
import java.sql.Timestamp

@SpringBootApplication
class SelfCareAssistantApplication

fun main(args: Array<String>) {
    runApplication<SelfCareAssistantApplication>(*args)
}
