package com.petlife.mall.service.impl;

import java.util.List;

import com.petlife.mall.dao.impl.CartDAOImpl;
import com.petlife.mall.entity.Cart;
import com.petlife.mall.service.CartService;
import com.petlife.user.entity.User;

public class CartServiceImpl implements CartService {
	private CartDAOImpl dao;

	public CartServiceImpl() {
		dao = new CartDAOImpl();
	}

	@Override
	public Integer add(Cart cart) {
		Integer id = dao.add(cart);
		cart = dao.findByPK(id);
		return id;
	}

	@Override
	public Integer delete(Integer cartId) {
		return dao.delete(cartId);
	}

	@Override
	public Integer update(Cart cart) {
		return dao.update(cart);
	}

	@Override
	public List<Cart> getAll() {
		return dao.getAll();
	}

	@Override
	 public List<Cart> getCartsByUser(User user) {
        return dao.getCartsByUser(user);
    }

	@Override
	public Cart findByPK(Integer cartId) {
		return dao.findByPK(cartId);
	}
	
	@Override
	public List<Cart> getCartsByUserAndSortBySeller(User user) {
		return dao.getCartsByUserAndSortBySeller(user);
	}

	@Override
	public Cart findByCommId(Integer commId) {
		return dao.findByCommId(commId);
	}
}
