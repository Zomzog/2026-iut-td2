package iut.nantes.exo0.controller

import iut.nantes.exo0.domain.Git
import iut.nantes.exo0.domain.Info
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InfoController(
    @Value("\${custom.app-name}") private val appName: String,
    @Value("\${custom.app-version}") private val appVersion: String,
    @Value("\${custom.git.branch}") private val gitBranch: String,
    @Value("\${custom.git.commit}") private val gitCommit: String,
) {

    @GetMapping("/api/v1/info")
    fun info() = Info(appName, appVersion, Git(gitBranch, gitCommit))
}
