package ma.imadsprojects.bankaccountservice;

import ma.imadsprojects.bankaccountservice.entities.BankAccount;
import ma.imadsprojects.bankaccountservice.entities.Customer;
import ma.imadsprojects.bankaccountservice.enums.AccountType;
import ma.imadsprojects.bankaccountservice.reposisoties.BankAccountRepository;
import ma.imadsprojects.bankaccountservice.reposisoties.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
        return args -> {
            Stream.of("mohemd", "imad","rihab").forEach(c->{
                Customer customer = Customer.builder()
                        .name(c)
                        .build();
                customerRepository.save(customer);
            });
                customerRepository.findAll().forEach(customer -> {
                    for (int i = 0; i < 10; i++) {
                        BankAccount bankAccount = BankAccount.builder()
                                .id(UUID.randomUUID().toString())
                                .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
                                .balance(10000+Math.random()*90000)
                                .createdAt(new Date())
                                .currency("MAD")
                                .customer(customer )
                                .build();
                        bankAccountRepository.save(bankAccount);
                    }

                });

        };
    }
}
