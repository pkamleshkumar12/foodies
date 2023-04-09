package com.ezcom.foodies.repository;

import com.ezcom.foodies.collection.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

    // Find restaurants by restaurant name
    List<Restaurant> findByNameIgnoreCaseContaining(String search);

    // Find restaurants by restaurant name sorted by delivery fee
    List<Restaurant> findByNameIgnoreCaseContainingOrderByDeliveryFeeAsc(String search);

    // Find restaurants by restaurant name sorted by estimated delivery time
    List<Restaurant> findByNameIgnoreCaseContainingOrderByEstimatedDeliveryTimeAsc(String search);

    // Find restaurants by restaurant name or item name
    @Query("{'$or':[ {'name': {'$regex': ?0, '$options': 'i'}}, {'items.name': {'$regex': ?0, '$options': 'i'}}]}")
    List<Restaurant> findByNameOrItemsName(String searchText);
}
