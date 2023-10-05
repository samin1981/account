package com.example.account.api.person;

import java.util.ArrayList;
import java.util.List;

public class GetAllPersonsResult {
    private List<GetPersonDetailResult> items = new ArrayList<>();

    public List<GetPersonDetailResult> getItems() {
        return items;
    }

    public void setItems(List<GetPersonDetailResult> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "GetAllPersonsResult{" +
                "items=" + items +
                '}';
    }
}

