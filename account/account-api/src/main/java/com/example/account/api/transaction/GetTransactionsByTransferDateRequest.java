package com.example.account.api.transaction;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.StringJoiner;

@Getter
@Setter
public class GetTransactionsByTransferDateRequest {
    @DateTimeFormat
    private Date transferDate;
        @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("transferDate=        " + transferDate)
                .toString();
    }
}
