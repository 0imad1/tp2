package ma.imadsprojects.bankaccountservice.web;

import ma.imadsprojects.bankaccountservice.DTO.BankAccountRequestDTO;
import ma.imadsprojects.bankaccountservice.DTO.BankAccountResponseDTO;
import ma.imadsprojects.bankaccountservice.Mappers.AccountMapper;
import ma.imadsprojects.bankaccountservice.entities.BankAccount;
import ma.imadsprojects.bankaccountservice.reposisoties.BankAccountRepository;
import ma.imadsprojects.bankaccountservice.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    private BankAccountRepository bankAccountRepository;
    private BankAccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, BankAccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping(path = "/bankAccounts")
    public List<BankAccount> bankAccounts(){
     return  bankAccountRepository.findAll();
    }
    @GetMapping(path = "/bankAccounts/{id}")
    public BankAccount bankAccounts(@PathVariable String id){
        return  bankAccountRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException(String.format("Account %s not found",id)));
    }

    @PostMapping(path = "/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO bankAccountRequestDTO){
        return accountService.addAccount(bankAccountRequestDTO);
    }
    @PutMapping (path = "/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id ,
                              @RequestBody BankAccount bankAccount){
        BankAccount account =bankAccountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance()!= null)
            account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedAt()!= null)
            account.setCreatedAt(new Date());
        if (bankAccount.getType()!= null)
            account.setType(bankAccount.getType());
        if (bankAccount.getCurrency()!= null)
            account.setCurrency(bankAccount.getCurrency() );
        return bankAccountRepository.save(account);

    }
    @DeleteMapping(path = "/bankAccounts/{id}")
    public void deletebankAccount(@PathVariable String id){
          bankAccountRepository.deleteById(id);
    }


}
