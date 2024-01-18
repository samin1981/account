package com.example.account.api.facility;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Getter
@Setter
public class GetFacilityResult {
    private List<GetFacilityResultItem> items = new ArrayList<>();

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("items=        " + (items != null ? items.size() : 0))
                .toString();
    }
}
