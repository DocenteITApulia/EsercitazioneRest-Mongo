package it.apulia.EsercitazioneRestMongo.services;

import it.apulia.EsercitazioneRestMongo.model.Libro;
import it.apulia.EsercitazioneRestMongo.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Libro getLibro(String bookId){
        return bookRepository.findById(bookId).get();
    }
    @Override
    public List<Libro> getLibri() {
        return bookRepository.findAll();
    }

    @Override
    public List<Libro> getLibriByAutore(String nomeAutore) {

        return bookRepository.findLibriByAutore(nomeAutore);
    }

    @Override
    public List<Libro> getLibriByAnno(Integer annoPB) {

        return bookRepository.findLibriByAnno(annoPB);
    }

    @Override
    public Libro getLibro(String autore, String titolo) {

        return bookRepository.findLibroByAutoreAndTitolo(autore,titolo);
    }

    @Override
    public void saveLibro(Libro libro) {
        bookRepository.save(libro);
    }

    @Override
    public void updateLibro(Libro libro){ //NON è OTTIMALE, però è il risultato è il comportamento atteso se presenti tutti i campi
        libro.setBookId(bookRepository.findLibroByAutoreAndTitolo(libro.getAutore(),libro.getTitolo()).getBookId());
        bookRepository.deleteLibroByAutoreAndTitolo(libro.getAutore(),libro.getTitolo());
        bookRepository.save(libro);
    }
    /*
    @Override
    @Transactional
    public void updateLibro(Libro libro) {
        //TODO da sistemare, non funziona a livello di persistenza del dato
        Libro temp = bookRepository.findLibroByAutoreAndTitolo(libro.getAutore(),libro.getTitolo());
        temp.setAnno(libro.getAnno());
        temp.setAutore(libro.getAutore());
        temp.setBookId(libro.getBookId());
        temp.setLink(libro.getLink());
        temp.setTitolo(libro.getTitolo());
        temp.setGenere(libro.getGenere());
    }
    */

    @Override
    public void deleteLibro(String autore, String titolo) {
        bookRepository.deleteLibroByAutoreAndTitolo(autore,titolo);
    }
}
