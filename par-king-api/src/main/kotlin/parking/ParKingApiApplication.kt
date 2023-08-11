package parking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
//@EnableDataSourceConfiguration
@ConfigurationPropertiesScan
class ParKingApiApplication

fun main(args: Array<String>) {
	System.setProperty("spring.config.name", "application-api")
	runApplication<ParKingApiApplication>(*args)
}
