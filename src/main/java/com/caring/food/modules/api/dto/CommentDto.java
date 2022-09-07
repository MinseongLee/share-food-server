package com.caring.food.modules.api.dto;

import com.caring.food.modules.comment.Comment;
import com.caring.food.modules.common.FoodType;
import com.caring.food.modules.restaurant.Restaurant;
import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long commentId;
    private String review;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .commentId(comment.getId())
                .review(comment.getReview())
                .build();
    }
}
