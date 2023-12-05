package com.petlife.buylistdetails.dao;

import java.util.List;

import com.petlife.buylistdetails.entity.BuylistDetails;




public class TestBuylistDetailsDAO {

	public static void main(String[] args) {
		BuylistDetailsDAO dao = new BuylistDetailsDAOImpl2();

		// 新增
		BuylistDetails buylistDetails1 = new BuylistDetails();
		buylistDetails1.setBuylistDetailsId(4);
		buylistDetails1.setBuylist(null);
		buylistDetails1.setComm(comm);
		buylistDetails1.setBuylistDetailsPrice(100);
		buylistDetails1.setBuylistDetailsPurchaseAmount(10);
		buylistDetails1.setMemberRatingStars(5.0);
		buylistDetails1.setBuyerEvaluateNarrative("你好");
		buylistDetails1.setBuyerEvaluateTime(java.sql.Timestamp.valueOf("2023-11-25 23:00:00"));
		buylistDetails1.setReturnReasons(null);
		dao.add(buylistDetails1);
//
//		// 修改
//		BuylistDetails cup2 = new BuylistDetails();
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
		
//		BuylistDetails buylistDetails = dao.findByPK(1);
//		System.out.print(buylistDetails.getBuylistDetailsId() + ",");
//		System.out.print(buylistDetails.getBuylistDetailsName() + ",");
//
//		System.out.println("---------------------");

//		// 查詢多筆
		List<BuylistDetails> list = dao.getAll();
		for (BuylistDetails buylistDetails : list) {
			System.out.println(buylistDetails.getBuylistDetailsId() + ",");
			System.out.println(buylistDetails.getBuylist()+",");
			System.out.println(buylistDetails.getComm()+",");
			

			System.out.println();
		}
	}

}