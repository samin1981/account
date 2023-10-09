package com.example.account.api.transaction;

import java.util.ArrayList;
import java.util.List;

public class GetAllTransactionsResult {
    private List<Transaction> items = new ArrayList<>();

    public List<Transaction> getItems() {
        return items;
    }

    public void setItems(List<Transaction> items) {
        this.items = items;
    }
}
