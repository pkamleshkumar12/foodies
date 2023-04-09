package com.ezcom.foodies.repository;

import com.ezcom.foodies.collection.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

    List<Restaurant> findByMenuNameContainingIgnoreCase(String searchText);

    Collection<? extends Restaurant> findByNameContainingIgnoreCase(String searchText);
}
