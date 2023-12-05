package com.petlife.cart.dao;
import com.petlife.cart.entity.Cart;
//import com.petlife.user.entity.User;
import com.petlife.user.entity.User;

public interface CartDAO {
	// 增
	public Integer add(Cart cart);
	
	// 刪
	public void delete(Integer cartId);
	
	// 改
	public void update(Cart cart);
	
	// 查(單個) 用UserId，暫時不寫用cardId搜尋，因為不合理。
	public Cart findByUserId(User user);

	
	// 查不會有多個
}