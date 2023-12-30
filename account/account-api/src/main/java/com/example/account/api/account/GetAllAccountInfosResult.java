package com.example.account.api.account;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAllAccountInfosResult {
    private List<GetAccountInfoDetailResult> items = new ArrayList<>();

    @Override
    public String toString() {
        return "GetAllAccountInfosResult{" +
                "items=" + items +
                '}';
    }
}
