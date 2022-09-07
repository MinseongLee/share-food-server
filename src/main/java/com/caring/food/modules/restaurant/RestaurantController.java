package com.caring.food.modules.restaurant;

import com.caring.food.modules.account.Account;
import com.caring.food.modules.account.CurrentAccount;
import com.caring.food.modules.common.FoodType;
import com.caring.food.modules.restaurant.dto.RestaurantForm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {
    private static final String RESTAURANT_INDEX = "restaurant/index";
    private static final String RESTAURANT_FORM = "restaurant/form";
    private static final String RESTAURANT_VIEW = "restaurant/view";
    private static final String RESTAURANT_UPDATE = "restaurant/update";
    private final RestaurantService restaurantService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String restaurants(Model model,
                             @PageableDefault(size = 9) Pageable pageable) {
        // pageable : size, page, sort
        Page<Restaurant> restaurants = restaurantService.getRestaurants(pageable);
        model.addAttribute("restaurants", restaurants);
        return RESTAURANT_INDEX;
    }

    // form 처리
    @GetMapping("/form")
    public String restaurantForm(Model model) {
        model.addAttribute(new RestaurantForm());
        return RESTAURANT_FORM;
    }

    // 실제 create 처리.
    @PostMapping("/form")
    public String createRestaurant(@CurrentAccount Account account, @Valid RestaurantForm restaurantForm, Errors errors) {
        if (errors.hasErrors()) {
            return RESTAURANT_FORM;
        }
        restaurantService.createNewRestaurant(restaurantForm, account);
        return "redirect:/restaurant";
    }

    @GetMapping("/korean")
    public String getKorean(Model model,
                            @PageableDefault(size = 9) Pageable pageable) {
        Page<Restaurant> restaurants = restaurantService.getRestaurantsWithType(pageable, FoodType.KOREAN);
        model.addAttribute("restaurants", restaurants);
        return RESTAURANT_INDEX;
    }
    @GetMapping("/chinese")
    public String getChinese(Model model,
                            @PageableDefault(size = 9) Pageable pageable) {
        Page<Restaurant> restaurants = restaurantService.getRestaurantsWithType(pageable, FoodType.CHINESE);
        model.addAttribute("restaurants", restaurants);
        return RESTAURANT_INDEX;
    }
    @GetMapping("/western")
    public String getWestern(Model model,
                            @PageableDefault(size = 9) Pageable pageable) {
        Page<Restaurant> restaurants = restaurantService.getRestaurantsWithType(pageable, FoodType.WESTERN);
        model.addAttribute("restaurants", restaurants);
        return RESTAURANT_INDEX;
    }
    @GetMapping("/japanese")
    public String getJapanese(Model model,
                            @PageableDefault(size = 9) Pageable pageable) {
        Page<Restaurant> restaurants = restaurantService.getRestaurantsWithType(pageable, FoodType.JAPANESE);
        model.addAttribute("restaurants", restaurants);
        return RESTAURANT_INDEX;
    }

    @GetMapping("/something")
    public String getSomething(Model model,
                              @PageableDefault(size = 9) Pageable pageable) {
        Page<Restaurant> restaurants = restaurantService.getRestaurantsWithType(pageable, FoodType.SOMETHING);
        model.addAttribute("restaurants", restaurants);
        return RESTAURANT_INDEX;
    }

    @GetMapping("/delivery")
    public String getDelivery(Model model,
                               @PageableDefault(size = 9) Pageable pageable) {
        Page<Restaurant> restaurants = restaurantService.getRestaurantsWithType(pageable, FoodType.DELIVERY);
        model.addAttribute("restaurants", restaurants);
        return RESTAURANT_INDEX;
    }

    @GetMapping("/{id}")
    public String detailsRestaurant(@PathVariable Long id, Model model) {
        // restaurant and comments
        Restaurant restaurant = restaurantService.getRestaurant(id);
        model.addAttribute(restaurant);
        return RESTAURANT_VIEW;
    }

    @GetMapping("/update/{id}")
    public String updateRestaurantForm(@PathVariable Long id, Model model) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        model.addAttribute(restaurant);
        model.addAttribute(modelMapper.map(restaurant, RestaurantForm.class));
        return RESTAURANT_UPDATE;
    }

    @PostMapping("/update/{id}")
    public String updateRestaurant(@PathVariable Long id, @Valid RestaurantForm restaurantForm, Errors errors, Model model) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        if (errors.hasErrors()) {
            model.addAttribute(restaurant);
            return RESTAURANT_UPDATE;
        }
        restaurantService.updateRestaurant(restaurant, restaurantForm);
        return "redirect:/restaurant";
    }
}
