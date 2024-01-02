package com.petlife.user.dao;

import java.util.List;

import com.petlife.user.entity.CreditCard;



public interface CreditCardDAO {
	//增
	public Integer add(CreditCard creditCard);
	//刪
	public Integer delete(Integer creditCard);
	//改
	public Integer update(CreditCard creditCard);
	//查(單個)
	public CreditCard findByPK(Integer creditCardId);
	public CreditCard findByUserId(Integer userId);
	//查(多個)
	public List<CreditCard> getAll();
	
}
