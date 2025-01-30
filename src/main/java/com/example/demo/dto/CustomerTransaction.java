package com.example.demo.dto;

import java.util.Date;

public record CustomerTransaction(
        long id,
        long customerId,
        Date transactionDate
) {
}
