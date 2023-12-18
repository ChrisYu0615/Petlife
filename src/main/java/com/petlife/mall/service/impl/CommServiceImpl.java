package com.petlife.mall.service.impl;

import java.util.List;

import com.petlife.mall.dao.CommDAO;
import com.petlife.mall.dao.impl.CommDAOImpl;
import com.petlife.mall.entity.Comm;
import com.petlife.mall.service.CommService;

public class CommServiceImpl implements CommService{
	private CommDAO dao;
	
	public CommServiceImpl() {
		dao = new CommDAOImpl();
	}

	@Override
	public Integer add(Comm comm) {
		Integer id = dao.add(comm);
		comm = dao.findByPk(id);
		return 0;
	}

	@Override
	public Integer delete(Integer commId) {
		// TODO Auto-generated method stub
		return dao.delete(commId);
	}

	@Override
	public Integer update(Comm comm) {
		// TODO Auto-generated method stub
		return dao.update(comm);
	}

	@Override
	public Comm findByPk(Integer commId) {
		// TODO Auto-generated method stub
		return dao.findByPk(commId);
	}

	@Override
	public List<Comm> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}
	
}
