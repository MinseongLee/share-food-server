package com.caring.food.modules.account;

import com.caring.food.modules.MockMvcTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@MockMvcTest
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @DisplayName("회원가입 화면 테스트")
    @Test
    void signUp() throws Exception {
        mockMvc.perform(get("/signup"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("account/signup"))
                .andExpect(unauthenticated());
    }

    @DisplayName("회원 가입 처리 - 입력값 오류 - email")
    @Test
    void signUp_wrong_email() throws Exception {
        mockMvc.perform(post("/signup")
                .param("email", "ema")
                .param("password", "12345")
                .param("confirmedNewPassword", "12345")
                .param("name", "dexter")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("account/signup"))
                .andExpect(unauthenticated());
    }

    @DisplayName("회원 가입 처리 - 입력값 오류 - password")
    @Test
    void signUp_wrong_password() throws Exception {
        mockMvc.perform(post("/signup")
                        .param("email", "dexter@gmail.com")
                        .param("password", "123457")
                        .param("confirmedNewPassword", "12345")
                        .param("name", "dexter")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("account/signup"))
                .andExpect(unauthenticated());
    }

    @DisplayName("회원 가입 처리 - 입력값 정상")
    @Test
    void signUp_correct() throws Exception {
        mockMvc.perform(post("/signup")
                .param("email", "dexter@gmail.com")
                .param("password", "12345")
                .param("confirmedNewPassword", "12345")
                .param("name", "dexter")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(authenticated().withUsername("dexter@gmail.com"));

        Account account = accountRepository.findByEmail("dexter@gmail.com");
        assertNotNull(account);
        assertNotEquals(account.getPassword(), "12345");
    }
}
