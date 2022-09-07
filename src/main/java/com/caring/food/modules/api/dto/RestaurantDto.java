package com.caring.food.modules.api.dto;

import com.caring.food.modules.common.FoodType;
import com.caring.food.modules.restaurant.Restaurant;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long restaurantId;
    private String name;
    private String description;
    private String url;
    private FoodType foodType;
    private List<CommentDto> commentList;

    public static RestaurantDto from(Restaurant restaurant) {
        return RestaurantDto.builder()
                .restaurantId(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .url(restaurant.getUrl())
                .foodType(restaurant.getFoodType())
                .commentList(restaurant.getCommentList().stream().map(CommentDto::from).collect((Collectors.toList())))
                .build();
    }
}
