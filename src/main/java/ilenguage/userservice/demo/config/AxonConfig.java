package ilenguage.userservice.demo.config;


import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ilenguage.userservice.demo.command.domain.User;

public class AxonConfig {

    @Bean
    public Repository<User> eventSourcingRepository(EventStore eventStore) {
        return EventSourcingRepository.builder(User.class)
                .eventStore(eventStore)
                .build();
    }

}
