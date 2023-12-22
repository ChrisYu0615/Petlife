package com.petlife.mall.dao;

import java.util.List;

import com.petlife.mall.entity.Cart;
import com.petlife.user.entity.User;

public interface CartDAO {
	//增
	public Integer add(Cart cart);
	//刪
	public Integer delete(Integer cartId);
	//改
	public Integer update(Cart cart);
	//查(單個)
	public Cart findByPK(Integer cartId);
	//查(多個)
	public List<Cart> getAll();
	public List<Cart> getCartsByUser(User user);
}
