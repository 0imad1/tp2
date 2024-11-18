package ma.imadsprojects.bankaccountservice.web;

import ma.imadsprojects.bankaccountservice.DTO.BankAccountRequestDTO;
import ma.imadsprojects.bankaccountservice.DTO.BankAccountResponseDTO;
import ma.imadsprojects.bankaccountservice.entities.BankAccount;
import ma.imadsprojects.bankaccountservice.entities.Customer;
import ma.imadsprojects.bankaccountservice.reposisoties.BankAccountRepository;
import ma.imadsprojects.bankaccountservice.reposisoties.CustomerRepository;
import ma.imadsprojects.bankaccountservice.service.BankAccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQlController {
    private BankAccountRepository bankAccountRepository;
    private BankAccountService bankAccountService;
    private CustomerRepository customerRepository;

    public BankAccountGraphQlController(BankAccountRepository bankAccountRepository, BankAccountService bankAccountService, CustomerRepository customerRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountService = bankAccountService;
        this.customerRepository = customerRepository;
    }

    @QueryMapping
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }
    @QueryMapping
    public BankAccount bankAccountByid(@Argument String id ){
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("ACCount string %s not valid", id)));
    }

    @MutationMapping
    public BankAccountResponseDTO   addAccount(@Argument BankAccountRequestDTO bankAccount){
        return bankAccountService.addAccount(bankAccount) ;

    }
    @MutationMapping
    public BankAccountResponseDTO   updateAccount(@Argument  String id,@Argument BankAccountRequestDTO bankAccount){

        return bankAccountService.updateAccount(id,bankAccount) ;

    }
    @MutationMapping
    public void   deleteAccount(@Argument  String id){
        bankAccountRepository.deleteById(id);
    }

    @QueryMapping
    public List<Customer> cutomers(){
      return   customerRepository.findAll();
    }
}
