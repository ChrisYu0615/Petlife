package com.petlife.forum.service;

import java.util.List;

import com.petlife.forum.entity.Comment;

public interface CommentService {
   

    // 增加評論
    public Comment addComment(Comment comment);
      

    // 刪除評論
    public Integer deleteComment(Integer commentId);

    // 更新評論
    public Integer updateComment(Comment comment);

    // 根據ID查詢評論
    public Comment getCommentById(Integer commentId);

    // 獲得所有評論
    public List<Comment> getAllComments(Integer articleId);
   

    // 可能的其他方法
    // ...
}
