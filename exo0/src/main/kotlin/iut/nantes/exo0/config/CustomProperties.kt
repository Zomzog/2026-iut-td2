package iut.nantes.exo0.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("custom")
data class CustomProperties(
    val appName: String,
    val appVersion: String,
    val git: GitProperties,
)

data class GitProperties(
    val branch: String,
    val commit: String,
)
