package com.petlife.admin.service;

import java.util.List;
import java.util.Map;

import com.petlife.admin.entity.Admin;

public interface AdminService {
	// 增
	Admin addAdmin(Admin admin);

	// 刪
	Integer deleteAdmin(Integer adminId);

	// 改
	Integer updateAdmin(Admin admin);

	// 查(單個)
	Admin getAdminByAdminId(Integer adminId);

	// 查(多個)
	List<Admin> getAllAdmins();

	// 管理員登入
	Map<String, Integer> adminLogin(String adminAcct, String adminPwd);

	boolean exisAdminAccount(String adminAcct);
	
	String getNewPwd(String adminAcct);
}
