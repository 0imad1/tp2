package ma.imadsprojects.bankaccountservice.web;

import ma.imadsprojects.bankaccountservice.entities.BankAccount;
import ma.imadsprojects.bankaccountservice.reposisoties.BankAccountRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQlController {
    private BankAccountRepository bankAccountRepository;

    public BankAccountGraphQlController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
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
}
