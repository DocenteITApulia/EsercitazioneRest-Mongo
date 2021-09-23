package it.apulia.EsercitazioneRestMongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EsercitazioneRestMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsercitazioneRestMongoApplication.class, args);
	}

/* Link per gestione logiche e errori
* https://stackabuse.com/how-to-return-http-status-codes-in-a-spring-boot-application/
* https://www.amitph.com/spring-return-specific-http-status/
* https://www.baeldung.com/spring-mvc-controller-custom-http-status-code --> soluzioni rapide (solo response entity o custom exception)
* https://www.baeldung.com/exception-handling-for-rest-with-spring
* 	--> esempio soluzione 4 con custom exception
* 	--> esempio soluzione 3 potrebbe utile se lanciamo nei servizi un certo tipo di eccezione che verrà raccolta da questa classe
*/


}
