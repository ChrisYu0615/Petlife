package com.petlife.admin.service.impl;

import com.petlife.admin.dao.AcctStateDAO;
import com.petlife.admin.dao.impl.AcctStateDAOImpl2;
import com.petlife.admin.entity.AcctState;
import com.petlife.admin.service.AcctStateService;

public class AcctStateServiceImpl implements AcctStateService {
	private AcctStateDAO dao;

	public AcctStateServiceImpl() {
		dao = new AcctStateDAOImpl2();
	}

	@Override
	public AcctState getByAcctStateId(Integer acctStateId) {
		return dao.findByPK(acctStateId);
	}

}
