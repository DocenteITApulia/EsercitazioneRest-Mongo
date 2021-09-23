package it.apulia.EsercitazioneRestMongo.controller;

import it.apulia.EsercitazioneRestMongo.model.Libro;
import it.apulia.EsercitazioneRestMongo.myexceptions.MyNotFoundExcp;
import it.apulia.EsercitazioneRestMongo.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
    public ResponseEntity<?> getLibriByAutore(@PathVariable String autore){
        try{
            List<Libro> temp = bookService.getLibriByAutore(autore);
            return ResponseEntity.ok().body(temp);
        }catch(MyNotFoundExcp excp){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excp.getMessage());
        }
    }


    @GetMapping("/ricerca/{anno}")
    public ResponseEntity<List<Libro>> getLibriByAnno(@PathVariable Integer anno){
        return ResponseEntity.ok().body(bookService.getLibriByAnno(anno));
    }

    @GetMapping("/ricerca/{autore}/{titolo}")
    public ResponseEntity<?> getLibro(@PathVariable String autore, @PathVariable String titolo){
        Libro temp = bookService.getLibro(autore,titolo);
        if(!(temp==null))
            return ResponseEntity.ok().body(temp);
        else //è un esempio
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Non è presente alcun autore con il nome indicato");
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
        Libro temp = bookService.getLibro(bookId);
        if(!(temp==null))
            return ResponseEntity.ok().body(temp);
        else
            return ResponseEntity.notFound().build();
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
