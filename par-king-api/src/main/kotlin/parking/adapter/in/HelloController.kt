package parking.adapter.`in`

import mu.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(): String {
        logger.info("test")
        return "hello"
    }

    companion object: KLogging()
}