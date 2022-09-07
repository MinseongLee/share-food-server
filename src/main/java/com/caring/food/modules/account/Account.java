package com.caring.food.modules.account;

import com.caring.food.modules.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "account")
public class Account extends BaseEntity {
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Builder
    public Account(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

}
