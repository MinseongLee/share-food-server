package com.caring.food.modules.api;

import com.caring.food.modules.account.Account;
import com.caring.food.modules.account.AccountRepository;
import com.caring.food.modules.account.CurrentAccount;
import com.caring.food.modules.api.dto.RestaurantDto;
import com.caring.food.modules.comment.CommentService;
import com.caring.food.modules.comment.dto.CommentDto;
import com.caring.food.modules.common.CustomResponse;
import com.caring.food.modules.common.exception.ApiErrorException;
import com.caring.food.modules.restaurant.Restaurant;
import com.caring.food.modules.restaurant.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class ApiController {

    private final RestaurantService restaurantService;
    private final CommentService commentService;

    private final AccountRepository accountRepository;

    @Operation(summary = "맛집 목록", description = "맛집 목록을 조회합니다.")
    @GetMapping("/restarurants")
    @ResponseBody
    public ResponseEntity<Page<RestaurantDto>> getRestaurants(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "9") Integer size) {
        Page<Restaurant> restaurants = restaurantService.getRestaurants(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
        return ResponseEntity.ok(restaurants.map(RestaurantDto::from));
    }

    @Operation(summary = "맛집 상세", description = "restaurantId에 해당하는 맛집 상세정보를 조회합니다.")
    @GetMapping("/restarurants/{restaurantId}")
    @ResponseBody
    public ResponseEntity<RestaurantDto> getRestaurantDetail(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        return ResponseEntity.ok(RestaurantDto.from(restaurant));
    }

    @Operation(summary = "후기 등록", description = "restaurantId에 해당하는 후기를 등록합니다.")
    @ResponseBody
    @PostMapping("/restarurants/{restaurantId}/comments")
    public ResponseEntity<CommentDto> createRestaurantComment(@PathVariable Long restaurantId, @Valid CommentDto commentDto, Errors errors) {
        if (errors.hasErrors()){
            throw new ApiErrorException("입력 내용을 확인해주세요.");
        }

        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);

        if(restaurant == null) {
            throw new ApiErrorException("해당 맛집이 존재하지 않습니다.");
        }

        Account account = accountRepository.findByEmail("test@caring.co.kr");

        if(account == null) {
            throw new ApiErrorException("해당 유저가 존재하지 않습니다.");
        }

        commentService.createComment(restaurant, account, commentDto);
        return ResponseEntity.ok(commentDto);
    }
}
