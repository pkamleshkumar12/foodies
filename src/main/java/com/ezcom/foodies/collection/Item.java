package com.ezcom.foodies.collection;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {

    private String name;
    private double price;
    private String imageUrl;
}
