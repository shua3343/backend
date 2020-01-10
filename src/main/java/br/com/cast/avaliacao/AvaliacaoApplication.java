package br.com.cast.avaliacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AvaliacaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(AvaliacaoApplication.class, args);
    }
}
