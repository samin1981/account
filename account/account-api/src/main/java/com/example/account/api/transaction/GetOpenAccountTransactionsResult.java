package com.example.account.api.transaction;

import java.util.List;

public class GetOpenAccountTransactionsResult {
    private List<GetOpenAccountTransactionsResultItem> items;

    public List<GetOpenAccountTransactionsResultItem> getItems() {
        return items;
    }
    public void setItems(List<GetOpenAccountTransactionsResultItem> items) {
        this.items = items;
    }
}
