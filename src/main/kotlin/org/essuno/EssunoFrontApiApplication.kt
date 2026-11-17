package org.essuno

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EssunoFrontApiApplication

fun main(args: Array<String>) {
    runApplication<EssunoFrontApiApplication>(*args)
}
