package ch.mobi.jap.envchecker

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class EnvcheckerApplication {

}

fun main(args: Array<String>) {
    SpringApplication.run(EnvcheckerApplication::class.java, *args)
}
