package it.apulia.EsercitazioneRestMongo.myexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MyNotFoundExcp extends RuntimeException{
    public MyNotFoundExcp() {
        super();
    }
    public MyNotFoundExcp(String message, Throwable cause) {
        super(message, cause);
    }
    public MyNotFoundExcp(String message) {
        super(message);
    }
    public MyNotFoundExcp(Throwable cause) {
        super(cause);
    }
}
