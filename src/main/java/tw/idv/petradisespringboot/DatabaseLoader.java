package tw.idv.petradisespringboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader {

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {};
    }
}
