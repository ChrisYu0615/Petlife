package com.petlife.forum.dao;

import java.util.List;
import com.petlife.forum.entity.Forum;

public interface ForumDAO {
    int add(Forum forum);
    int update(Forum forum);
    int delete(Integer forumId);
    Forum findByPK(Integer forumId);
    List<Forum> getAll();
}
