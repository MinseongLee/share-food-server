package com.caring.food.modules.comment;

import com.caring.food.modules.account.Account;
import com.caring.food.modules.comment.dto.CommentDto;
import com.caring.food.modules.restaurant.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void createComment(Restaurant restaurant, Account account, CommentDto commentDto) {
        Comment comment = Comment.builder()
                .review(commentDto.getReview())
                .account(account)
                .restaurant(restaurant)
                .build();
        commentRepository.save(comment);
    }
}
