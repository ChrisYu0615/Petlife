package com.petlife.test;

import com.petlife.cart.dao.CartDAOImpl;
import com.petlife.cart.entity.Cart;
import com.petlife.comm.dao.CommDAOImpl;
import com.petlife.comm.entity.Comm;
import com.petlife.user.dao.UserDAOImpl;
import com.petlife.user.entity.User;

public class TestCartDAO {
	public static void main(String[] args) {
		CartDAOImpl dao = new CartDAOImpl();
		UserDAOImpl userDAO = new UserDAOImpl();
		CommDAOImpl commDAOImpl = new CommDAOImpl();
		// 撈出userId=100000001
		User user = userDAO.findByPK(100000001);
		// 使用commId=7
		Comm comm = commDAOImpl.findByPk(7);
		
		// 新增
//		Cart cart = new Cart();
//		cart.setUser(user);
//		cart.setComm(comm);
//		cart.setPurchasingAmout(20);
//		Integer id = dao.add(cart);
//		System.out.println("Add CartId = "+id+" successfully.");
		
		// 刪除
		int deletedPk = 12;
		dao.delete(deletedPk);

		// 更改
//		int updatePk = 1;
//		Cart cart1 = new Cart();
//		cart1.setCartId(updatePk);
//		cart1.setUser(user);
//		cart1.setComm(comm);
//		cart1.setPurchasingAmout(30);
//		dao.update(cart1);

		// 查詢
//		Cart cart2 = dao.findByUserId(user);
//		System.out.println("找到的comm name: " + cart2.getCartId());
		
		// 找多個
//		List<Comm> resultList = dao.getAll();
//		for(Comm comm : resultList) {
//			System.out.println("Id = " + comm.getCommId());
//		}
	}
}
