package com.caring.food.modules.comment;


import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CommentRepositoryExtensionImpl extends QuerydslRepositorySupport implements CommentRepositoryExtension {
    public CommentRepositoryExtensionImpl() {
        super(Comment.class);
    }
}
