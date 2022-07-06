package group.teafc.teabot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TeabotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeabotApplication.class, args);
    }
}
