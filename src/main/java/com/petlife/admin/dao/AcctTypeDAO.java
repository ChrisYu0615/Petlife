package com.petlife.admin.dao;

import java.util.List;

import com.petlife.admin.entity.AcctType;

public interface AcctTypeDAO {
	// 增
	public Integer add(AcctType acctType);

	// 刪
	public Integer delete(Integer acctTypeId);

	// 改
	public Integer update(AcctType acctType);

	// 查(單個)
	public AcctType findByPK(Integer acctTypeId);

	// 查(多個)
	public List<AcctType> getAll();
}
