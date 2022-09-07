package com.caring.food.modules.comment;


import com.caring.food.modules.MockMvcTest;
import com.caring.food.modules.account.Account;
import com.caring.food.modules.account.AccountRepository;
import com.caring.food.modules.account.WithAccount;
import com.caring.food.modules.restaurant.Restaurant;
import com.caring.food.modules.restaurant.RestaurantFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcTest
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RestaurantFactory restaurantFactory;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @WithAccount(value = "dexter@gmail.com")
    @DisplayName("리뷰 생성")
    void createComment() throws Exception {
        Account account = accountRepository.findByEmail("dexter@gmail.com");
        Restaurant restaurant = restaurantFactory.createRestaurant(account);
        mockMvc.perform(post("/comment/"+restaurant.getId())
                        .param("review", "맛있어")
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}
