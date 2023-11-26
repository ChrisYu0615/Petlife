package com.petlife.user.dao;

import java.util.List;

import com.petlife.user.entity.User;

public interface UserDAO {
	// 增
	public Integer add(User user);

	// 刪
	public Integer delete(Integer userId);

	// 改
	public Integer update(User user);

	// 查(單個)
	public User findByPK(Integer userId);
	public User findUserByUserNickname(String userNickname);
	public User findUserByUserAccount(String userAccount);

	// 查(多個)
	public List<User> getAll();
	
	// 取得總數(count)
	public Long getTotal();

}
