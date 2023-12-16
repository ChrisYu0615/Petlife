package com.petlife.mall.service.impl;

import java.util.List;

import com.petlife.mall.dao.CommDAO;
import com.petlife.mall.dao.impl.CommDAOImpl;
import com.petlife.mall.entity.Comm;
import com.petlife.mall.service.CommService;
import com.petlife.user.dao.impl.UserDAOImpl2;

public class CommServiceImpl implements CommService{
	private CommDAO commDAO;
	
	public CommServiceImpl() {
		commDAO = new CommDAOImpl();
	}
	// 查全部
	public List<Comm> getAll(){
		return commDAO.getAll();
	}
	
	// 以下似乎是嘉仁要寫的部分
	// 加商品是不是要由嘉仁寫R???
	@Override
	public Integer add(Comm comm) {
		return 1;
	}
	
	@Override
	public Integer delete(Integer commId) {
		return 1;
	}
	@Override
	public Integer update(Comm comm) {
		return 1;
	}
	
	@Override
	public Comm findByPk(Integer commId) {
		return null;
	}
}
