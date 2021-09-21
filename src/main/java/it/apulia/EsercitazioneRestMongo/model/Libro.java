package it.apulia.EsercitazioneRestMongo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@NoArgsConstructor //necessario in quanto se mancante dava un errore durante le richieste
public class Libro {

    String titolo;
    @Indexed
    String autore;
    @Id
    String bookId;
    Integer anno;
    String link;
    List<String> genere;

    public Libro(String titolo, String autore, Integer anno, String link, List<String> genere) {
        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        this.link = link;
        this.genere = genere;
    }

    public Libro(String titolo, String autore, Integer anno, String link) {
        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        this.link = link;
    }
}
