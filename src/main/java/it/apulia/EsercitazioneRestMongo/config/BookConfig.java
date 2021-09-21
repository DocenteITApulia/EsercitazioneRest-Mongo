package it.apulia.EsercitazioneRestMongo.config;

import it.apulia.EsercitazioneRestMongo.model.Libro;
import it.apulia.EsercitazioneRestMongo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BookConfig {
    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository) {
        return args -> {
            Libro libro1 = new Libro("It","S.King",1993, "linkamazon");
            Libro libro2 = new Libro("Shining","S.King",1991, "linkamazon");

            List<Libro> temp= new ArrayList<>();
            temp.add(libro1);
            temp.add(libro2);


            repository.deleteAll();

            repository.saveAll(
                    temp
            );
        };

    }
}
