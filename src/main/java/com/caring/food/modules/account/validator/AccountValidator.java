package com.caring.food.modules.account.validator;

import com.caring.food.modules.account.AccountRepository;
import com.caring.food.modules.account.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class AccountValidator implements Validator {

    private final AccountRepository accountRepository;


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(AccountDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountDto accountDto = (AccountDto) target;
        if (accountRepository.existsByEmail(accountDto.getEmail())){
            errors.rejectValue("email", "invalid.email", new Object[]{accountDto.getEmail()}, "이미 사용중인 이메일이 존재합니다.");
        }

        if (!accountDto.getPassword().equals(accountDto.getConfirmedNewPassword())) {
            errors.rejectValue("confirmedNewPassword", "wrong.value", "패스워드를 일치시켜주세요.");
        }
    }
}
