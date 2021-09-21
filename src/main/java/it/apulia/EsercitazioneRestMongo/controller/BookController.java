package it.apulia.EsercitazioneRestMongo.controller;

import it.apulia.EsercitazioneRestMongo.model.Libro;
import it.apulia.EsercitazioneRestMongo.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/bookManager")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/library")
    public ResponseEntity<List<Libro>> getLibrary(){

        return ResponseEntity.ok().body(bookService.getLibri());
    }


    @GetMapping("/library/{autore}")
    public ResponseEntity<List<Libro>> getLibriByAutore(@PathVariable String autore){
        return ResponseEntity.ok().body(bookService.getLibriByAutore(autore));
    }


    @GetMapping("/ricerca/{anno}")
    public ResponseEntity<List<Libro>> getLibriByAnno(@PathVariable Integer anno){
        return ResponseEntity.ok().body(bookService.getLibriByAnno(anno));
    }

    @GetMapping("/ricerca/{autore}/{titolo}")
    public ResponseEntity<Libro> getLibro(@PathVariable String autore, @PathVariable String titolo){
        return ResponseEntity.ok().body(bookService.getLibro(autore,titolo));
    }

    @PostMapping("/library")
    public ResponseEntity<Libro> addLibro(@RequestBody Libro libro){
        bookService.saveLibro(libro);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/bookManager/ricerca/" +libro.getAutore() + "/" + libro.getTitolo()).toUriString());
                //.path("/bookManager/library/" +libro.getAutore() + "/" + libro.getTitolo()).toUriString());

        log.info("Libro {} salvato all'interno della libreria raggiungibile al link {} ",libro.getTitolo(), uri.toString());
       return ResponseEntity.created(uri).body(libro);
        //return ResponseEntity.ok().body(libro);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Libro> getLibroById(@PathVariable String bookId){
        return ResponseEntity.ok().body(bookService.getLibro(bookId));
    }

    @PutMapping("/{autore}/{titolo}")
    public ResponseEntity<Libro> updateLibro(@RequestBody Libro libro){
        bookService.updateLibro(libro);
        return ResponseEntity.ok().body(libro);
    }

    @DeleteMapping("/{autore}/{titolo}")
    public ResponseEntity<?> deleteLibro(@PathVariable String autore, @PathVariable String titolo){
        bookService.deleteLibro(autore, titolo);
        return ResponseEntity.ok().build();
    }
}
