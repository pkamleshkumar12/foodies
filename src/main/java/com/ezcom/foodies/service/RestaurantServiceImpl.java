package com.ezcom.foodies.service;

import com.ezcom.foodies.collection.Promotion;
import com.ezcom.foodies.collection.Restaurant;
import com.ezcom.foodies.collection.Tag;
import com.ezcom.foodies.exception.ResourceNotFoundException;
import com.ezcom.foodies.repository.PromotionRepository;
import com.ezcom.foodies.repository.RestaurantRepository;
import com.ezcom.foodies.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant assignTagToRestaurant(String id, String tagId) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", id));
        Optional<Tag> tagOptional = tagRepository.findById(tagId);
        if (tagOptional.isPresent()) {
            Tag tag = tagOptional.get();
            if (!restaurant.getTags().contains(tag)) {
                restaurant.getTags().add(tag);
                restaurantRepository.save(restaurant);
            }
        }
        else {
            throw new ResourceNotFoundException("Tag", "id", tagId);
        }
        return  restaurant;
    }

    @Override
    public Restaurant assignPromotionToRestaurant(String id, String promotionId) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", id));
        Optional<Promotion> promotionOptional = promotionRepository.findById(promotionId);
        if (promotionOptional.isPresent()) {
            Promotion promotion = promotionOptional.get();
            if (!restaurant.getPromotions().contains(promotion)) {
                restaurant.getPromotions().add(promotion);
                restaurantRepository.save(restaurant);
            }
        }
        else {
            throw new ResourceNotFoundException("Promotion", "id", promotionId);
        }
        return  restaurant;

    }

    @Override
    public Restaurant removeTagFromRestaurant(String id, String tagId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            List<Tag> tags = restaurant.getTags();
            tags.removeIf(tag -> tag.getId().equals(tagId));
            restaurant.setTags(tags);
            return restaurantRepository.save(restaurant);
        } else {
            throw new ResourceNotFoundException("Restaurant", "id", id);
        }
    }

    @Override
    public Restaurant removePromotionFromRestaurant(String id, String promotionId) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            List<Promotion> promotions = restaurant.getPromotions();
            promotions.removeIf(promotion -> promotion.getId().equals(promotionId));
            restaurant.setPromotions(promotions);
            return restaurantRepository.save(restaurant);
        } else {
            throw new ResourceNotFoundException("Restaurant", "id", id);
        }
    }

    @Override
    public List<Restaurant> searchRestaurants(String searchText, boolean sortByFastestDelivery, boolean filterByPromotionExist, String tagId) {
        List<Restaurant> restaurants = restaurantRepository.findByNameOrItemsName(searchText);
        if (filterByPromotionExist) {
            restaurants = restaurants.stream().filter(r -> r.getPromotions() != null && !r.getPromotions().isEmpty())
                    .collect(Collectors.toList());
        }
        if (tagId != null) {
            Optional<Tag> tag = tagRepository.findById(tagId);
            if (tag.isPresent()) {
                restaurants = restaurants.stream().filter(r -> r.getTags() != null && r.getTags().contains(tag.get()))
                        .collect(Collectors.toList());
            }
        }
        if (sortByFastestDelivery) {
            restaurants.sort(Comparator.comparingInt(Restaurant::getEstimatedDeliveryTime));
        }
        return restaurants;
    }
}
