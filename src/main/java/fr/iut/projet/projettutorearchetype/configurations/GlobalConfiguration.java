package fr.iut.projet.projettutorearchetype.configurations;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class GlobalConfiguration {
}
