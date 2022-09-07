package com.caring.food.modules.comment;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CommentRepositoryExtension {
}
