package com.petlife.user.service.impl;

import java.util.List;

import com.petlife.user.dao.CreditCardDAO;
import com.petlife.user.dao.impl.CreditCardDAOImpl;
import com.petlife.user.entity.CreditCard;
import com.petlife.user.service.CreditCardService;



public class CreditCardServiceImpl implements CreditCardService {
	private CreditCardDAO dao;

	public CreditCardServiceImpl() {
		dao = new CreditCardDAOImpl();
	}

	@Override
	public CreditCard addCreditCard(CreditCard creditCard) {
		Integer id = dao.add(creditCard);
		creditCard = dao.findByPK(id);
		return creditCard;
	}

	@Override
	public Integer deleteCreditCard(Integer creditCardId) {
		return dao.delete(creditCardId);
	}

	@Override
	public Integer updateCreditCard(CreditCard creditCard) {
		return dao.update(creditCard);
	}

	@Override
	public CreditCard getCreditCardByCreditCardId(Integer creditCardId) {
		return dao.findByPK(creditCardId);
	}

	@Override
	public List<CreditCard> getAllCreditCards() {
		return dao.getAll();
	}
}
