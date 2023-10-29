package com.example.account.api.transaction;

import java.util.StringJoiner;

public class GetTransactionsByTransferDateResult extends GetAllTransactionsResult {
    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .toString();
    }
}
