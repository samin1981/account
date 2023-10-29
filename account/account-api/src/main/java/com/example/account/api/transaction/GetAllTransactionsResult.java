package com.example.account.api.transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class GetAllTransactionsResult {
    private List<TransactionResult> items = new ArrayList<>();

    public List<TransactionResult> getItems() {
        return items;
    }

    public void setItems(List<TransactionResult> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("items=        " + items)
                .toString();
    }
}
