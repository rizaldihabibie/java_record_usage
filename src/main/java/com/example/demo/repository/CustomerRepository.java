package com.example.demo.repository;

import com.example.demo.dto.CustomerRecord;
import com.example.demo.dto.CustomerTransaction;
import com.example.demo.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    // Records not suitable for mapping query result from DB
    // This code to show how we are able to use Records for mapping the result
    record UserRowFromDB(Long id, String name, String address, String phoneNumber, Date birthDate){}

    @Query(

            "SELECT new com.example.demo.repository.CustomerRepository$UserRowFromDB(" +
                    "c.id, c.name, c.address, c.phoneNumber, c.birthDate) " +
                    "FROM CustomerEntity c where id = :id"
    )
    Optional<UserRowFromDB> loadCustomerData(long id);

    @Query("SELECT new com.example.demo.dto.CustomerTransaction(t.id, t.customerId, t.transactionDate)" +
            "FROM CustomerTransactionEntity t WHERE customerId = :customerId")
    List<CustomerTransaction> getCustomerTransaction(long customerId);

    default Optional<CustomerRecord> findCustomerRecord(long id) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        var customer = loadCustomerData(id);
        var transactions = customer
                .map(cstm -> getCustomerTransaction(cstm.id()))
                .map(HashSet::new)
                .orElse(new HashSet<>());

        return customer.map(cstm -> new CustomerRecord(
                cstm.id(),
                cstm.name(),
                cstm.address(),
                cstm.phoneNumber(),
                cstm.birthDate(),
                transactions
        )
        );

    }
}
