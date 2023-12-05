package com.petlife.test;

import java.util.List;

import com.petlife.buyliststate.dao.BuylistStateDAO;
import com.petlife.buyliststate.dao.BuylistStateDAOImpl2;
import com.petlife.buyliststate.entity.BuylistState;


public class TestBuylistStateDAO {

	public static void main(String[] args) {
		BuylistStateDAO dao = new BuylistStateDAOImpl2();

		// 新增
//		CouponVO cup1 = new CouponVO();
//		cup1.setCouponId(10);
//		cup1.setCouponName("Hello");
//		cup1.setCouponContent("輸入Hello，嗨");
//		cup1.setConditionsOfUse(1);
//		cup1.setStartDate(java.sql.Timestamp.valueOf("2023-11-24 23:00:00"));
//		cup1.setEndDate(java.sql.Timestamp.valueOf("2023-11-25 23:00:00"));
//		cup1.setDiscountAmount(999);
//		dao.add(cup1);
//
//		// 修改
//		CouponVO cup2 = new CouponVO();
//		cup2.setCouponId(4);
//		cup2.setCouponName("aaaa");
//		cup2.setCouponContent("輸入aaaa，嗨");
//		cup2.setConditionsOfUse(1);
//		cup2.setStartDate(java.sql.Timestamp.valueOf("2023-11-24 23:00:00"));
//		cup2.setEndDate(java.sql.Timestamp.valueOf("2023-11-25 23:00:00"));
//		cup2.setDiscountAmount(20);
//		dao.update(cup2);

//
//		// 刪除
//		dao.delete(5);

//		// 查詢單筆
		
//		BuylistState buylistState = dao.findByPK(1);
//		System.out.print(buylistState.getBuylistStateId() + ",");
//		System.out.print(buylistState.getBuylistStateName() + ",");
//
//		System.out.println("---------------------");

//		// 查詢多筆
		List<BuylistState> list = dao.getAll();
		for (BuylistState buylistState : list) {
			System.out.print(buylistState.getBuylistStateId() + ",");
			System.out.print(buylistState.getBuylistStateName() + ",");

			System.out.println();
		}
	}

}