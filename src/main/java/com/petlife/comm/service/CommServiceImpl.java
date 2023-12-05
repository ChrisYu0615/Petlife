package com.petlife.comm.service;

import com.google.protobuf.Service;
import com.petlife.comm.dao.CommDAO;
import com.petlife.comm.dao.CommDAOImpl;
import com.petlife.test.TestCommDAO;

public abstract class CommServiceImpl implements Service{
	private CommDAO dao;
	
	public  CommServiceImpl() {
		dao = new CommDAOImpl();
	}
}
