package reactive.microservices.accountservice.payload;

import org.springframework.data.annotation.Id;
import reactive.microservices.accountservice.model.Account;

public class AccountRequest {

  @Id
  private String id;
  private String number;
  private String customerId;
  private int amount;

  public AccountRequest() {
    super();
  }

  public Account toAccount() {
    return new Account(this.number, this.customerId, this.amount);
  }


  public AccountRequest(String number, String customerId, int amount) {
    this.number = number;
    this.customerId = customerId;
    this.amount = amount;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}
