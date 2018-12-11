package reactive.microservices.accountservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactive.microservices.accountservice.model.Account;
import reactor.core.publisher.Flux;

public interface AccountRepository extends ReactiveCrudRepository<Account, String> {

  Flux<Account> findByCustomerId(String customerId);

}
