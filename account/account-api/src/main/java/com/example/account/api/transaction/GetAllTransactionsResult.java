package com.example.account.api.transaction;

import java.util.ArrayList;
import java.util.List;

public class GetAllTransactionsResult {
    private List<TransactionResult> items = new ArrayList<>();

    public List<TransactionResult> getItems() {
        return items;
    }

    public void setItems(List<TransactionResult> items) {
        this.items = items;
    }
}
