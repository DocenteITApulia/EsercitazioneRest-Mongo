package it.apulia.EsercitazioneRestMongo.services;

import it.apulia.EsercitazioneRestMongo.model.Libro;

import java.util.List;

public interface BookService {

    List<Libro> getLibri();

    List<Libro> getLibriByAutore(String nomeAutore);

    List<Libro> getLibriByAnno(Integer annoPB);

    Libro getLibro(String bookId);

    Libro getLibro(String autore, String titolo);

    void saveLibro(Libro libro);

    void updateLibro(Libro libro);

    void deleteLibro(String autore, String titolo);
}
