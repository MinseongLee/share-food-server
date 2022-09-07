package com.caring.food.modules.comment;


import com.caring.food.modules.account.Account;
import com.caring.food.modules.account.CurrentAccount;
import com.caring.food.modules.comment.dto.CommentDto;
import com.caring.food.modules.common.CustomResponse;
import com.caring.food.modules.restaurant.Restaurant;
import com.caring.food.modules.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final RestaurantService restaurantService;

    @ResponseBody
    @PostMapping("/{id}")
    public CustomResponse createComment(@PathVariable Long id, @CurrentAccount Account account, @Valid CommentDto commentDto, Errors errors) {
        if (errors.hasErrors()){
            return CustomResponse.customResponse("400", "리뷰를 입력해주세요.");
        }
        Restaurant restaurant = restaurantService.getRestaurant(id);
        commentService.createComment(restaurant, account, commentDto);
        return CustomResponse.defaultCustomResponse();
    }

}
