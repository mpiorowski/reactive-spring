package reactive.microservices.customerservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactive.microservices.customerservice.model.Customer;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, String> {

}
