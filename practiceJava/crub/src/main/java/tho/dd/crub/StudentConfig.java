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
            Student alice = new Student(1L, "alice", "alice@gmail.com", LocalDate.of(2010, 5, 4), 21);
            Student bob = new Student(2L, "bob", "bob@yahoo.com", LocalDate.of(2010, 5, 4), 23);
            repository.saveAll(
                    List.of(alice, bob)
            );
        };
    }
}
