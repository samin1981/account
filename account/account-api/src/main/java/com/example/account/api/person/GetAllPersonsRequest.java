package com.example.account.api.person;

import java.util.StringJoiner;

public class GetAllPersonsRequest {
    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .toString();
    }
}
