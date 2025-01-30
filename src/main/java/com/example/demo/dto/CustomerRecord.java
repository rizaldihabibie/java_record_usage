package com.example.demo.dto;

import java.util.Date;
import java.util.Set;

public record CustomerRecord(
        long id,
        String name,
        String address,
        String phoneNumber,
        Date birthDate,
        Set<CustomerTransaction> customerTransactions
) {
}
