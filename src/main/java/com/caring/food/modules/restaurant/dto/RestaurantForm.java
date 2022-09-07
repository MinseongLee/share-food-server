package com.caring.food.modules.restaurant.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class RestaurantForm {
    @NotBlank
    @Length(max = 200)
    private String name;

    private String description;

    private String url;

    @NotBlank
    private String foodType;
}
