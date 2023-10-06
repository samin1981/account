package com.example.account.api.account;

import java.util.ArrayList;
import java.util.List;

public class GetAllAccountInfosResult {
    private List<GetAccountInfoDetailResult> items = new ArrayList<>();

    public List<GetAccountInfoDetailResult> getItems() {
        return items;
    }

    public void setItems(List<GetAccountInfoDetailResult> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "GetAccountDetailResult{" +
                "items=" + items +
                '}';
    }
}
