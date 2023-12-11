package com.petlife.admin.dao;

import java.util.List;

import com.petlife.admin.entity.Admin;
import com.petlife.user.entity.User;

public interface AdminDAO {
	// 增
	public Integer add(Admin admin);

	// 刪
	public Integer delete(Integer adminId);

	// 改
	public Integer update(Admin admin);

	// 查單個(Id)
	public Admin findByPK(Integer adminId);

	// 查單個(帳號)
	public Admin findByAdminAccount(String adminAcct);

	// 查單個(帳號&密碼)
	public Admin findByAdminAccountAndPassword(String adminAcct, String adminPwd);

	// 查(多個)
	public List<Admin> getAll();
}
