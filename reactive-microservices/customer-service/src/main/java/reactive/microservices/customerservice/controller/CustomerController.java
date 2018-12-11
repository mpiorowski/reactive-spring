package reactive.microservices.customerservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactive.microservices.customerservice.mapper.CustomerMapper;
import reactive.microservices.customerservice.model.Account;
import reactive.microservices.customerservice.model.Customer;
import reactive.microservices.customerservice.repository.CustomerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController {

  private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  @Autowired private CustomerRepository customerRepository;

  @Autowired private WebClient.Builder webClientBuilder;

  @GetMapping("/{id}")
  public Mono<Customer> findById(@PathVariable("id") String id) {
    logger.info("findById: id={}", id);
    return customerRepository.findById(id);
  }

  @GetMapping
  public Flux<Customer> findAll() {
    logger.info("findAll");
    return customerRepository.findAll();
  }

  @GetMapping("/{id}/with-accounts")
  public Mono<Customer> findByIdWithAccounts(@PathVariable("id") String id) {
    logger.info("findByIdWithAccounts: id={}", id);
    Flux<Account> accounts =
        webClientBuilder
            .build()
            .get()
            .uri("http://account-service/customer/{customer}", id)
            .retrieve()
            .bodyToFlux(Account.class);
    return accounts
        .collectList()
        .map(val -> new Customer(val))
        .mergeWith(customerRepository.findById(id))
        .collectList()
        .map(CustomerMapper::map);
  }

  @PostMapping
  public Mono<Customer> create(@RequestBody Customer customer) {
    logger.info("create: {}", customer);
    return customerRepository.save(customer);
  }
}
