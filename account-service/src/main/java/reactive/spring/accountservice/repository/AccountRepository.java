package reactive.spring.accountservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactive.spring.accountservice.model.Account;
import reactor.core.publisher.Flux;

public interface AccountRepository extends ReactiveCrudRepository<Account, String> {

  Flux<Account> findByCustomerId(String customerId);

}
