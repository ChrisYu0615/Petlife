package com.petlife.forum.service;

import java.util.List;
import com.petlife.forum.entity.Forum;

public interface ForumService {
   
    // 增加論壇
    public Forum addForum(Forum forum);
      
    // 刪除論壇
    public Integer deleteForum(Integer forumId);

    // 更新論壇
    public Integer updateForum(Forum forum);

    // 根據ID查詢論壇
    public Forum getForumById(Integer forumId);

    // 獲得所有論壇
    public List<Forum> getAllForums();
    //根據Sortname找論壇
    public Forum findForumBySortName(String sortName);
   
    // 可能的其他方法
    // ...
}
