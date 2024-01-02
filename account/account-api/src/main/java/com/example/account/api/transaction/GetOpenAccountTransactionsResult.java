package com.example.account.api.transaction;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.StringJoiner;

@Getter
@Setter
public class GetOpenAccountTransactionsResult {
    private List<GetOpenAccountTransactionsResultItem> items;
    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("items=        " + items)
                .toString();
    }
}
