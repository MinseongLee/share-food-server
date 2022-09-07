package com.caring.food.modules.comment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CommentDto {
    @NotBlank
    private String review;
}
