package com.caring.food.modules.restaurant;

import com.caring.food.modules.account.Account;
import com.caring.food.modules.common.FoodType;
import com.caring.food.modules.restaurant.dto.RestaurantForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantService {

    private final ModelMapper modelMapper;

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant createNewRestaurant(RestaurantForm restaurantForm, Account account) {
        FoodType foodType = getFoodType(restaurantForm.getFoodType());
        if (foodType==null) throw new IllegalArgumentException("올바른 타입이 아닙니다.");
        Restaurant restaurant = Restaurant.builder()
                                    .name(restaurantForm.getName())
                                    .description(restaurantForm.getDescription())
                                    .url(restaurantForm.getUrl())
                                    .foodType(foodType)
                                    .account(account)
                                    .build();
        return restaurantRepository.save(restaurant);
    }

    private FoodType getFoodType(String foodType) {
        switch (foodType) {
            case "KOREAN":
                return FoodType.KOREAN;
            case "CHINESE":
                return FoodType.CHINESE;
            case "WESTERN":
                return FoodType.WESTERN;
            case "JAPANESE":
                return FoodType.JAPANESE;
            case "SOMETHING":
                return FoodType.SOMETHING;
            case "DELIVERY":
                return FoodType.DELIVERY;
        }
        return null;
    }
    public Page<Restaurant> getRestaurants(Pageable pageable) {
        return restaurantRepository.findByOrderByDesc(pageable);
    }

    public Page<Restaurant> getRestaurantsWithType(Pageable pageable, FoodType foodType) {
        return restaurantRepository.findByWithFoodType(pageable, foodType);
    }

    public Restaurant getRestaurant(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (!restaurant.isPresent()) {
            throw new IllegalArgumentException("해당하는 식당이 존재하지 않습니다.");
        }
        return restaurant.get();
    }

    @Transactional
    public void updateRestaurant(Restaurant restaurant, RestaurantForm restaurantForm) {
        modelMapper.map(restaurantForm, restaurant);
    }
}
