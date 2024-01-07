package com.petlife.mall.service;

import java.util.List;

import com.petlife.mall.entity.Cart;
import com.petlife.user.entity.User;

public interface CartService {
	//增
	public Integer add(Cart cart);
	//刪
	public Integer delete(Integer cartId);
	//改
	public Integer update(Cart cart);
	//查(單個)
	public Cart findByPK(Integer cartId);
	public Cart findByCommId(Integer commId);
	public Cart findCartByCommIdAndUserId(Integer commId, Integer userId);
	//查(多個)
	public List<Cart> getAll();
	public List<Cart> getCartsByUser(User user);
	public List<Cart> getCartsByUserAndSortBySeller(User user); 
}
