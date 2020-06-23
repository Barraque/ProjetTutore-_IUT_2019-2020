package fr.iut.projet.projettutorearchetype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class ServerApplication {

    public static void main(String[] args) {
        System.out.println("Server starting...");
        SpringApplication.run(ServerApplication.class, args);
    }

}