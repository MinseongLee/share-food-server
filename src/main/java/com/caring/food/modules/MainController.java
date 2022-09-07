package com.caring.food.modules;

import com.caring.food.modules.account.Account;
import com.caring.food.modules.account.AccountService;
import com.caring.food.modules.account.CurrentAccount;
import com.caring.food.modules.account.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    private final AccountService accountService;

    @GetMapping
    public String index(@CurrentAccount Account account, Model model) {
        if (account != null) {
            model.addAttribute(account);
        }
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}
