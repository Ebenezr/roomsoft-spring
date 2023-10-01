package ebenezr.dev.roomsoftspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RoomsoftSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomsoftSpringApplication.class, args);
    }

}
