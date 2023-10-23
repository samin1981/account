package com.example.account.api.facility;

import java.util.List;

public class GetFacilityResult {
    private List<GetFacilityResultItem> items;

    public List<GetFacilityResultItem> getItems() {
        return items;
    }

    public void setItems(List<GetFacilityResultItem> items) {
        this.items = items;
    }
}
