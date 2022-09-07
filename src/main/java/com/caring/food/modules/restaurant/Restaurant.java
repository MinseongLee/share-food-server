package com.caring.food.modules.restaurant;

import com.caring.food.modules.account.Account;
import com.caring.food.modules.comment.Comment;
import com.caring.food.modules.common.BaseEntity;
import com.caring.food.modules.common.FoodType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "food_type")
    private FoodType foodType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @OneToMany(mappedBy = "restaurant")
    private final List<Comment> commentList = new ArrayList<>();

    @Builder
    public Restaurant(String name, String description, String url, FoodType foodType, Account account) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.foodType = foodType;
        if (account != null) addAccount(account);
    }

    private void addAccount(Account account) {
        this.account = account;
    }
}
