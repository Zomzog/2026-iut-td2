package iut.nantes.exo0.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(CustomProperties::class)
class PropertiesConfig
