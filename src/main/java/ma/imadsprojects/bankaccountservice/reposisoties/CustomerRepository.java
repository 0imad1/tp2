package ma.imadsprojects.bankaccountservice.reposisoties;

import lombok.extern.java.Log;
import ma.imadsprojects.bankaccountservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
