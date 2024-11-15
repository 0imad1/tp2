package ma.imadsprojects.bankaccountservice.service;

import ma.imadsprojects.bankaccountservice.DTO.BankAccountRequestDTO;
import ma.imadsprojects.bankaccountservice.DTO.BankAccountResponseDTO;
import ma.imadsprojects.bankaccountservice.Mappers.AccountMapper;
import ma.imadsprojects.bankaccountservice.entities.BankAccount;
import ma.imadsprojects.bankaccountservice.reposisoties.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {
     private BankAccountRepository bankAccountRepository;
     private AccountMapper accountMapper;
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountdto) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountdto.getBalance())
                .type(bankAccountdto.getType())
                .currency(bankAccountdto.getCurrency())
                .build();
        BankAccount savedBanKaccount= bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO= accountMapper.fromBankAccount(savedBanKaccount);
        return bankAccountResponseDTO;
    }
}
