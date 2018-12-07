package reactive.spring.customerservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactive.spring.customerservice.model.Customer;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, String> {

}
