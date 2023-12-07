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
	
	// 查一般會員暱稱是否重複
	public User findUserByUserNickname(String userNickname);
	
	// 查一般會員帳號是否重複
	public User findUserByUserAccount(String userAcct);
	
	// 查一般會員帳號密碼是否存在
	public User findUserByUserAccountAndPassword(String userAcct,String userPwd);

	// 查(多個)
	public List<User> getAll();
	
	// 取得總數(count)
	public Long getTotal();

}
