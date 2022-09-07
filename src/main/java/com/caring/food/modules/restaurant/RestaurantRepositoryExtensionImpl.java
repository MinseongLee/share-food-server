package com.caring.food.modules.restaurant;

import com.caring.food.modules.common.FoodType;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Objects;

public class RestaurantRepositoryExtensionImpl extends QuerydslRepositorySupport implements RestaurantRepositoryExtension {

    public RestaurantRepositoryExtensionImpl() {
        super(Restaurant.class);
    }

    @Override
    public Page<Restaurant> findByOrderByDesc(Pageable pageable) {
        QRestaurant restaurant = QRestaurant.restaurant;
        JPQLQuery<Restaurant> query = from(restaurant).orderBy(restaurant.createdAt.desc());
        JPQLQuery<Restaurant> pageableQuery = Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, query);
        QueryResults<Restaurant> results = pageableQuery.fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public Page<Restaurant> findByWithFoodType(Pageable pageable, FoodType foodType) {
        QRestaurant restaurant = QRestaurant.restaurant;
        JPQLQuery<Restaurant> query = from(restaurant).where(restaurant.foodType.eq(foodType))
                .orderBy(restaurant.createdAt.desc());
        JPQLQuery<Restaurant> pageableQuery = Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, query);
        QueryResults<Restaurant> results = pageableQuery.fetchResults();
        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
