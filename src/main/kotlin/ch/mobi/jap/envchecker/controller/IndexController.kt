package ch.mobi.jap.envchecker.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringBootVersion
import org.springframework.core.env.Environment
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
class IndexController(val environment: Environment,
                      @Value("\${envchecker.version}") val envcheckerVersion: String) {

    @GetMapping("/")
    fun index(model: Model,
              @RequestParam(required = false) sourceEnv : String?,
              @RequestParam(required = false) targetEnv: String?): String {

        model.addAttribute("envcheckerVersion", envcheckerVersion)
        model.addAttribute("bootVersion", SpringBootVersion.getVersion())
        model.addAttribute("activeProfiles", Arrays.toString(environment.activeProfiles))

        return "index"
    }

}