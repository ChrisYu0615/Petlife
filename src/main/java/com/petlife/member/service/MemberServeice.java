package com.petlife.member.service;

import java.util.List;

import com.petlife.member.entity.Member;

public interface MemberServeice {

	// 增
	Member addUser(Member user);

	// 刪
	Integer deleteUser(Integer userId);

	// 改
	Integer updateUser(Member user);

	// 查(單個)
	Member getUserByUserId(Integer userId);

	// 查(多個)
	List<Member> getAllUsers();
	
	int getPageTotal();

	boolean existUserNickname(String userNickname);
	
	boolean exisUserAccount(String userAccount);
}
