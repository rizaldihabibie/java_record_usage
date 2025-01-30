package com.example.demo.repository;

import com.example.demo.dto.CustomerRecord;
import com.example.demo.dto.CustomerTransaction;
import com.example.demo.entity.CustomerEntity;
import com.example.demo.entity.CustomerTransactionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerTransactionRepository customerTransactionRepository;

    @Test
    void fetchData() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("habibie");
        customerEntity.setAddress("Semarang, Central Java");
        customerEntity.setBirthDate(simpleDateFormat.parse("1990-09-18"));
        customerEntity.setPhoneNumber("6281345343242");
        CustomerEntity customerEntity2 = new CustomerEntity();
        customerEntity2.setName("habibie");
        customerEntity2.setAddress("Semarang, Central Java");
        customerEntity2.setBirthDate(simpleDateFormat.parse("1990-09-18"));
        customerEntity2.setPhoneNumber("6281345343242");

        List<CustomerEntity> lists = new ArrayList<>();
        lists.add(customerEntity2);
        lists.add(customerEntity);

        List<CustomerEntity> customerResults = customerRepository.saveAll(lists);

        Date currentDate = new Date();

        CustomerTransactionEntity customerTransactionEntity = new CustomerTransactionEntity();
        customerTransactionEntity.setCustomerId(customerResults.get(0).getId());
        customerTransactionEntity.setTransactionDate(currentDate);
        customerTransactionRepository.save(customerTransactionEntity);

        Optional<CustomerRecord> customerRecordOptional = customerRepository.findCustomerRecord(customerResults.get(0).getId());
        assertTrue(customerRecordOptional.isPresent());
        assertFalse(customerRecordOptional.get().customerTransactions().isEmpty());
        for (CustomerTransaction transaction : customerRecordOptional.get().customerTransactions()) {
            assertEquals(currentDate, transaction.transactionDate());
        }


    }
}
