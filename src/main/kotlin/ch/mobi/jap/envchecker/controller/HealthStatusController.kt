package ch.mobi.jap.envchecker.controller

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthStatusController {

    @GetMapping("/rest/health/status")
    fun healthStatus(model: Model) {
    }

}