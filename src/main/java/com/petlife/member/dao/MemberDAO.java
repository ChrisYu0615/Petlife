package com.petlife.member.dao;

import java.util.List;

import com.petlife.member.entity.Member;

public interface MemberDAO {
	// 增
	public Integer add(Member user);

	// 刪
	public Integer delete(Integer userId);

	// 改
	public Integer update(Member user);

	// 查(單個)
	public Member findByPK(Integer userId);
	public Member findUserByUserNickname(String userNickname);
	public Member findUserByUserAccount(String userAccount);

	// 查(多個)
	public List<Member> getAll();
	
	// 取得總數(count)
	public Long getTotal();

}
