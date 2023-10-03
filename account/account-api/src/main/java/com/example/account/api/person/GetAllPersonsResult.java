package com.example.account.api.person;

import java.util.ArrayList;
import java.util.List;

public class GetAllPersonsResult {
    private List<Person> items = new ArrayList<>();

    public List<Person> getItems() {
        return items;
    }

    public void setItems(List<Person> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "GetAllPersonsResult{" +
                "items=" + items +
                '}';
    }
}

