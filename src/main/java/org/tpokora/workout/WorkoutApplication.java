package org.tpokora.workout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
}
)
public class WorkoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkoutApplication.class, args);
    }

}