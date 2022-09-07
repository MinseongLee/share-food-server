package com.caring.food.modules.account.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AccountDto {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 4, max = 50)
    private String password;

    @NotBlank
    @Length(min = 4, max = 50)
    private String confirmedNewPassword;

    @NotBlank
    @Length(min = 1, max = 20)
    private String name;
}
