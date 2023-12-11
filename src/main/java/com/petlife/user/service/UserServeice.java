package com.petlife.user.service;

import java.util.List;
import java.util.Map;

import com.petlife.user.entity.User;

public interface UserServeice {

	// 增
	User addUser(User user);

	// 刪
	Integer deleteUser(Integer userId);

	// 改
	Integer updateUser(User user);

	// 查(單個)
	User getUserByUserId(Integer userId);

	// 查(多個)
	List<User> getAllUsers();

	boolean existUserNickname(String userNickname);

	boolean exisUserAccount(String userAcct);

	// 會員登入
	Map<String, Integer> userLogin(String userAcct, String userPwd);

	String getNewPwd(String userAcct);
}
