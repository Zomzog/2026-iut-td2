package iut.nantes.exo0.controller

import iut.nantes.exo0.config.CustomProperties
import iut.nantes.exo0.domain.Git
import iut.nantes.exo0.domain.Info
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InfoController(private val properties: CustomProperties) {

    @GetMapping("/api/v1/info")
    fun info() = Info(
        properties.appName,
        properties.appVersion,
        Git(properties.git.branch, properties.git.commit),
    )
}
