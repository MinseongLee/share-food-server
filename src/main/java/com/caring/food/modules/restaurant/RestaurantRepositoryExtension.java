package com.caring.food.modules.restaurant;

import com.caring.food.modules.common.FoodType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface RestaurantRepositoryExtension {
    Page<Restaurant> findByOrderByDesc(Pageable pageable);
    Page<Restaurant> findByWithFoodType(Pageable pageable, FoodType foodType);
}
