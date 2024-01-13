package com.example.account.api.account;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

@Getter
@Setter
public class GetAllAccountInfosRequest {
    @NotNull
    @Min(0)
    private int startIndex;

    @NotNull
    @Min(1)
    private int pageSize;
    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("startIndex=        " + startIndex)
                .add("pageSize=        " + pageSize)
                .toString();
    }
}
