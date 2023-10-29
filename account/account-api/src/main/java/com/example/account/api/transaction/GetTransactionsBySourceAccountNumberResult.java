package com.example.account.api.transaction;

import java.util.StringJoiner;

public class GetTransactionsBySourceAccountNumberResult extends GetAllTransactionsResult{
    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .toString();
    }
}
