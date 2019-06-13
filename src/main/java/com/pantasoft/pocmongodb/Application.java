package com.pantasoft.pocmongodb;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Stream;

interface ReservationRepository extends MongoRepository<Reservation, String> {
}

@SpringBootApplication
@Log4j2
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Log4j2
@Component
class MongoDbDemo {

    private final ReservationRepository rr;

    MongoDbDemo(ReservationRepository rr) {
        this.rr = rr;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void demo() throws Exception {

        this.rr.deleteAll();
        Faker faker = new Faker();

        Stream.of(faker.name().firstName(), faker.name().firstName(), faker.name().firstName(), faker.name().firstName())
                .map(name -> new Reservation(UUID.randomUUID().toString(), name))
                .map(this.rr::save)
                .forEach(log::info);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reservations")
class Reservation {

    @Id
    private String id;
    private String name;
}
