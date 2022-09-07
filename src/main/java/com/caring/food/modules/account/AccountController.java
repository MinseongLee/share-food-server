package com.caring.food.modules.account;

import com.caring.food.modules.account.dto.AccountDto;
import com.caring.food.modules.account.validator.AccountValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private static final String ACCOUNT_SIGNUP = "account/signup";

    private final AccountValidator accountValidator;
    private final AccountService accountService;

    @InitBinder("accountDto")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(accountValidator);
    }


    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute(new AccountDto());
        return ACCOUNT_SIGNUP;
    }

    @PostMapping("/signup")
    public String createAccount(@Valid AccountDto accountDto, Errors errors) {
        if (errors.hasErrors()) {
            return ACCOUNT_SIGNUP;
        }

        Account account = accountService.processNewAccount(accountDto);
        accountService.login(account);
        return "redirect:/";
    }
}
