package com.example.account.api.base;

import java.util.StringJoiner;

public class TrackingCodeResult {
    private String trackingCode;

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("trackingCode=        '" + trackingCode + "'")
                .toString();
    }
}
