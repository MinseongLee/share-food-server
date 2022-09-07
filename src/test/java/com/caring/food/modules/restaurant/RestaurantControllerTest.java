package com.caring.food.modules.restaurant;

import com.caring.food.modules.MockMvcTest;
import com.caring.food.modules.account.Account;
import com.caring.food.modules.account.AccountRepository;
import com.caring.food.modules.account.WithAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockMvcTest
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RestaurantFactory restaurantFactory;

    @Autowired
    private AccountRepository accountRepository;

    @AfterEach
    void afterEach() { accountRepository.deleteAll(); }

    @Test
    @WithAccount(value = "dexter@gmail.com")
    @DisplayName("식당 조회")
    void showRestaurants() throws Exception {
        mockMvc.perform(get("/restaurant"))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurant/index"))
                .andExpect(model().attributeExists("restaurants"));
    }

    @Test
    @WithAccount(value = "dexter@gmail.com")
    @DisplayName("한식 식당 조회")
    void showKorean() throws Exception {
        mockMvc.perform(get("/restaurant/korean"))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurant/index"))
                .andExpect(model().attributeExists("restaurants"));
    }

    @Test
    @WithAccount(value = "dexter@gmail.com")
    @DisplayName("중식 식당 조회")
    void showChinese() throws Exception {
        mockMvc.perform(get("/restaurant/chinese"))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurant/index"))
                .andExpect(model().attributeExists("restaurants"));
    }

    @Test
    @WithAccount(value = "dexter@gmail.com")
    @DisplayName("양식 식당 조회")
    void showWestern() throws Exception {
        mockMvc.perform(get("/restaurant/western"))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurant/index"))
                .andExpect(model().attributeExists("restaurants"));
    }

    @Test
    @WithAccount(value = "dexter@gmail.com")
    @DisplayName("일식 식당 조회")
    void showJapanese() throws Exception {
        mockMvc.perform(get("/restaurant/japanese"))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurant/index"))
                .andExpect(model().attributeExists("restaurants"));
    }

    @Test
    @WithAccount(value = "dexter@gmail.com")
    @DisplayName("기타 식당 조회")
    void showSomething() throws Exception {
        mockMvc.perform(get("/restaurant/something"))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurant/index"))
                .andExpect(model().attributeExists("restaurants"));
    }

    @Test
    @WithAccount(value = "dexter@gmail.com")
    @DisplayName("배달 조회")
    void showDelivery() throws Exception {
        mockMvc.perform(get("/restaurant/delivery"))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurant/index"))
                .andExpect(model().attributeExists("restaurants"));
    }

    @Test
    @WithAccount(value = "dexter@gmail.com")
    @DisplayName("식당 등록 - 폼 조회")
    void restaurantForm() throws Exception {
        mockMvc.perform(get("/restaurant/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurant/form"))
                .andExpect(model().attributeExists("restaurantForm"));
    }
    @Test
    @WithAccount(value = "dexter@gmail.com")
    @DisplayName("식당 등록 - 입력값 정상")
    void createRestaurant() throws Exception {
        mockMvc.perform(post("/restaurant/form")
                .param("name", "일일향")
                .param("description", "볶음밥")
                .param("url", "google.com")
                .param("foodType", "SOMETHING")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/restaurant"));
    }

    @Test
    @WithAccount(value = "dexter@gmail.com")
    @DisplayName("식당 상세 검색")
    void detailRestaurant() throws Exception {
        Account account = accountRepository.findByEmail("dexter@gmail.com");
        Restaurant restaurant = restaurantFactory.createRestaurant(account);
        mockMvc.perform(get("/restaurant/"+restaurant.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurant/view"))
                .andExpect(model().attributeExists("restaurant"));
    }

    @Test
    @WithAccount(value = "dexter@gmail.com")
    @DisplayName("식당 수정 폼")
    void updateRestaurantForm() throws Exception {
        Account account = accountRepository.findByEmail("dexter@gmail.com");
        Restaurant restaurant = restaurantFactory.createRestaurant(account);
        mockMvc.perform(get("/restaurant/update/"+restaurant.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurant/update"))
                .andExpect(model().attributeExists("restaurant"))
                .andExpect(model().attributeExists("restaurantForm"));
    }


    @Test
    @WithAccount(value = "dexter@gmail.com")
    @DisplayName("식당 수정")
    void updateRestaurant() throws Exception {
        Account account = accountRepository.findByEmail("dexter@gmail.com");
        Restaurant restaurant = restaurantFactory.createRestaurant(account);
        mockMvc.perform(post("/restaurant/update/"+restaurant.getId())
                .param("name", "일일향")
                .param("description", "볶음밥")
                .param("url", "google.com")
                .param("foodType", "KOREAN")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/restaurant"));
    }
}
