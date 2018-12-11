package reactive.microservices.accountservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactive.microservices.accountservice.model.Account;
import reactive.microservices.accountservice.payload.AccountRequest;
import reactive.microservices.accountservice.repository.AccountRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AccountController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

  @Autowired
  private AccountRepository repository;

  @GetMapping("/customer/{customer}")
  public Flux<Account> findByCustomer(@PathVariable("customer") String customerId) {
    LOGGER.info("findByCustomer: customerId={}", customerId);
    return repository.findByCustomerId(customerId);
  }

  @GetMapping
  public Flux<Account> findAll() {
    LOGGER.info("findAll");
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public Mono<Account> findById(@PathVariable("id") String id) {
    LOGGER.info("findById: id={}", id);
    return repository.findById(id);
  }

  @PostMapping
  public Mono<Account> create(@RequestBody AccountRequest accountRequest) {
    Account account = accountRequest.toAccount();
    LOGGER.info("create: {}", account);
    return repository.save(account);
  }

}