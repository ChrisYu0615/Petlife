package com.petlife.admin.dao;

import java.util.List;

import com.petlife.admin.entity.Admin;

public interface AdminDAO {
	// 增
	public Integer add(Admin admin);

	// 刪
	public Integer delete(Integer adminId);

	// 改
	public Integer update(Admin admin);

	// 查(單個)
	public Admin findByPK(Integer adminId);

	// 查(多個)
	public List<Admin> getAll();
}
