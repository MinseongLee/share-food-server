package com.caring.food.modules.comment;

import com.caring.food.modules.account.Account;
import com.caring.food.modules.common.BaseEntity;
import com.caring.food.modules.restaurant.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends BaseEntity {
    @Column(name = "review")
    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    // 한 번 조회할 떄 한 번에 가져오기 위해서
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Builder
    public Comment(String review, Account account, Restaurant restaurant) {
        this.review = review;
        if (account != null) this.account = account;
        if (restaurant != null) this.restaurant = restaurant;
    }
}
