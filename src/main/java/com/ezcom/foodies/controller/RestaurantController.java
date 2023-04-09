package com.ezcom.foodies.controller;

import com.ezcom.foodies.collection.Item;
import com.ezcom.foodies.collection.Restaurant;
import com.ezcom.foodies.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {


    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(@RequestParam("text") String searchText,
                                                              @RequestParam(value = "sortByFastestDelivery", required = false) Boolean sortByFastestDelivery,
                                                              @RequestParam(value = "promotion", required = false) Boolean hasPromotion,
                                                              @RequestParam(value = "tag", required = false) String tag) {
        List<Restaurant> restaurants = restaurantService.searchRestaurants(searchText, sortByFastestDelivery, hasPromotion, tag);
        return ResponseEntity.ok(restaurants);
    }





    @PostMapping("/")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant savedRestaurant = restaurantService.addRestaurant(restaurant);
        return ResponseEntity.ok(savedRestaurant);
    }

    // Update an existing restaurant
    @PutMapping("/")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurant);
        return ResponseEntity.ok(updatedRestaurant);
    }

    // Assign a tag to a restaurant
    @PutMapping("/{id}/assign/tag/{tagId}")
    public ResponseEntity<Restaurant> assignTagToRestaurant(@PathVariable String id, @PathVariable String tagId) {
        Restaurant updatedRestaurant = restaurantService.assignTagToRestaurant(id, tagId);
        return ResponseEntity.ok(updatedRestaurant);
    }

    // Assign a promotion to a restaurant
    @PutMapping("/{id}/assign/promotion/{promotionId}")
    public ResponseEntity<Restaurant> assignPromotionToRestaurant(@PathVariable String id, @PathVariable String promotionId) {
        Restaurant updatedRestaurant = restaurantService.assignPromotionToRestaurant(id, promotionId);
        return ResponseEntity.ok(updatedRestaurant);
    }

    // Remove a tag from a restaurant
    @DeleteMapping("/{id}/tags/{tagId}")
    public ResponseEntity<Restaurant> removeTagFromRestaurant(@PathVariable String id, @PathVariable String tagId) {
        Restaurant updatedRestaurant = restaurantService.removeTagFromRestaurant(id, tagId);
        return ResponseEntity.ok(updatedRestaurant);
    }

    // Remove a promotion from a restaurant
    @DeleteMapping("/{id}/promotions/{promotionId}")
    public ResponseEntity<Restaurant> removePromotionFromRestaurant(@PathVariable String id, @PathVariable String promotionId) {
        Restaurant updatedRestaurant = restaurantService.removePromotionFromRestaurant(id, promotionId);
        return ResponseEntity.ok(updatedRestaurant);
    }

}
