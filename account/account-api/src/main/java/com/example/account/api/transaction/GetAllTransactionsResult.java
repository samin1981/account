package com.example.account.api.transaction;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Getter
@Setter
public class GetAllTransactionsResult {
    private List<TransactionResult> items = new ArrayList<>();

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("items=        " + (items != null ? items.size() : 0))
                .toString();
    }
}
