package com.example.account.api.base;

import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

@Getter
@Setter
public class TrackingCodeResult {
    private String trackingCode;

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("trackingCode=        '" + trackingCode + "'")
                .toString();
    }
}
