package tho.dd.crub;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student alice = new Student(1L,
                    "Alice",
                    "alice@gmail.com",
                    LocalDate.of(2014, 1, 2),
                    21);
            Student bob = new Student(2L,
                    "Bob",
                    "bob.alister@gmail.com",
                    LocalDate.of(2011, 1, 2),
                    21);

            repository.saveAll(List.of(alice, bob));
        };
    }
}
