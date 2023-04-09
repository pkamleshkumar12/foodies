package com.ezcom.foodies.service;

import com.ezcom.foodies.collection.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant addRestaurant(Restaurant restaurant);

    Restaurant updateRestaurant(Restaurant restaurant);

    Restaurant assignTagToRestaurant(String id, String tagId);

    Restaurant assignPromotionToRestaurant(String id, String promotionId);

    Restaurant removeTagFromRestaurant(String id, String tagId);

    Restaurant removePromotionFromRestaurant(String id, String promotionId);

    List<Restaurant> searchRestaurants(String searchText, boolean sortByFastestDelivery, boolean filterByPromotionExist, String tagId);
}
