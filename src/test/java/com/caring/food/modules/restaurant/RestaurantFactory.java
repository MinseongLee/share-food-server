package com.caring.food.modules.restaurant;

import com.caring.food.modules.account.Account;
import com.caring.food.modules.common.FoodType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantFactory {
    @Autowired
    private final RestaurantRepository restaurantRepository;

    public Restaurant createRestaurant(Account account) {
        Restaurant restaurant = Restaurant.builder()
                .name("굴밥")
                .description("맛있어")
                .url("google.com")
                .foodType(FoodType.KOREAN)
                .account(account)
                .build();
        restaurantRepository.save(restaurant);
        return restaurant;
    }
}
