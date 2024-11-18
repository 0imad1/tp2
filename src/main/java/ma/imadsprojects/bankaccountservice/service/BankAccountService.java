package ma.imadsprojects.bankaccountservice.service;

import ma.imadsprojects.bankaccountservice.DTO.BankAccountRequestDTO;
import ma.imadsprojects.bankaccountservice.DTO.BankAccountResponseDTO;
import ma.imadsprojects.bankaccountservice.entities.BankAccount;
import ma.imadsprojects.bankaccountservice.enums.AccountType;

public interface BankAccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountdto);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountdto);
}
