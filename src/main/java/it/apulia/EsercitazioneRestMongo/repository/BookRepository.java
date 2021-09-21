package it.apulia.EsercitazioneRestMongo.repository;

import it.apulia.EsercitazioneRestMongo.model.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Libro, String> {

    Libro findLibroByAutoreAndTitolo(String autore,String titolo);

    @Query("{ 'autore' : ?0 }")
    List<Libro> findLibriByAutore(String autore);

    @Query("{ 'anno' : ?0 }")
    List<Libro> findLibriByAnno(Integer anno);

    void deleteLibroByAutoreAndTitolo(String autore, String titolo);

}
