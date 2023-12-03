package com.petlife.forum.dao;

import com.petlife.forum.entity.Forum;

public class TestForum {
    public static void main(String[] args) {
        ForumDAO dao = new ForumDAOImpl();
        
        // 新增
//        Forum forum = new Forum();
//        forum.setSortName("測試");
//        dao.add(forum);
        // 修改
//        Forum forumToUpdate = new Forum();
//        forumToUpdate.setForumId(1); // 假设存在ID为1的论坛
//        forumToUpdate.setSortName("更新后的论坛名称");
//        boolean updateResult = dao.update(forumToUpdate);
//        if (updateResult) {
//            System.out.println("论坛更新成功");
//        } else {
//            System.out.println("论坛更新失败");
//        }

      //修改
//        Forum forum1 = new Forum();
//        forum1.setSortName(null);
//        dao.add(forum1);
//        
        // 删除
        dao.delete(5);

        // 查詢多筆
//        List<Forum> forums = dao.getAll();
//        for (Forum f : forums) {
//            System.out.println("论坛ID: " + f.getForumId() + ", 名称: " + f.getSortName());
//        }
    }
}
