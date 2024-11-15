package ma.imadsprojects.bankaccountservice.DTO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.imadsprojects.bankaccountservice.enums.AccountType;

import java.util.Date;
@Data
@NoArgsConstructor@AllArgsConstructor@Builder
public class BankAccountResponseDTO {
    private String id;
    private Date createdAt;
    private Double balance;
    private String currency;
    private AccountType type;
}
