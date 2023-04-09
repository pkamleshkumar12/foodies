package com.ezcom.foodies.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@Document("Restaurant")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Restaurant {

    @Id
    private String id;
    private String name;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Point location;
    private Integer estimatedDeliveryTime;
    private BigInteger deliveryFee;
    private List<Tag> tags;
    private List<Double> ratings;
    private List<Promotion> promotions;
    private List<Item> menu;
}
