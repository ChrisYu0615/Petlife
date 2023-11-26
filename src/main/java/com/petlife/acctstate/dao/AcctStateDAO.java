package com.petlife.acctstate.dao;

import java.util.List;

import com.petlife.acctstate.entity.AcctState;

public interface AcctStateDAO {
	//增
	public Integer add(AcctState acctState);
	//刪
	public Integer delete(Integer acctStateId);
	//改
	public Integer update(AcctState acctState);
	//查(單個)
	public AcctState findByPK(Integer acctStateId);
	//查(多個)
	public List<AcctState> getAll();
}
