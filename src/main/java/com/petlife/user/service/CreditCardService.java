package com.petlife.user.service;

import java.util.List;

import com.petlife.user.entity.CreditCard;

public interface CreditCardService {
	// 增
	CreditCard addCreditCard(CreditCard creditCard);

	// 刪
	Integer deleteCreditCard(Integer creditCardId);

	// 改
	Integer updateCreditCard(CreditCard creditCard);

	// 查(單個)
	CreditCard getCreditCardByCreditCardId(Integer creditCardId);
	public CreditCard findByUserId(Integer userId);

	// 查(多個)
	List<CreditCard> getAllCreditCards();
}
