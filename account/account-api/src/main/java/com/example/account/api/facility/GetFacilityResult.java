package com.example.account.api.facility;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.StringJoiner;

@Getter
@Setter
public class GetFacilityResult {
    private List<GetFacilityResultItem> items;

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("items=        " + items)
                .toString();
    }
}
