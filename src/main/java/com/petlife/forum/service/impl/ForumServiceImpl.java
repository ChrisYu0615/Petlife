package com.petlife.forum.service.impl;

import java.util.List;

import com.petlife.forum.dao.ForumDAO;
import com.petlife.forum.dao.impl.ForumDAOImpl;
import com.petlife.forum.entity.Forum;
import com.petlife.forum.service.ForumService;

public class ForumServiceImpl implements ForumService {
    private ForumDAO dao;

    public ForumServiceImpl() {
        dao = new ForumDAOImpl();
    }

    @Override
    public Forum addForum(Forum forum) {
        Integer id = dao.add(forum);
        if(id != null && id != -1) {
            return dao.findByPK(id);
        }
        return null;
    }

    @Override
    public Integer deleteForum(Integer forumId) {
        return dao.delete(forumId);
    }

    @Override
    public Integer updateForum(Forum forum) {
        return dao.update(forum);
    }

    @Override
    public Forum getForumById(Integer forumId) {
        return dao.findByPK(forumId);
    }

    @Override
    public List<Forum> getAllForums() {
        return dao.getAll();
    }

    // 可能的其他方法
    // ...
}
