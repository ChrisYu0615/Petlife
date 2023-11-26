package com.petlife.test;

import java.util.List;
import java.util.Set;

import com.petlife.acctstate.entity.AcctState;
import com.petlife.accttype.entity.AcctType;
import com.petlife.user.dao.UserDAO;
import com.petlife.user.dao.UserDAOImpl;
import com.petlife.user.entity.User;

public class TestUserDAO {
	public static void main(String[] args) {

		UserDAO dao = new UserDAOImpl();
		// 新增資料
//		User user = new User();
//		user.setUserAcct("a1231@gmail.com");
//		user.setUserPwd("qwer1234");
//		user.setUserName("易吉小壞貓");
//		user.setUserNickName("超派");
//		user.setBirthday(java.sql.Date.valueOf("2020-11-21"));
//		user.setAddress("台北市內湖區港前路一段xx號xx樓");
//		user.setPhoneNum("0917383662");
//		user.setGender(true);
//
//		int addRow = dao.add(user);
//		if (addRow >= 1) {
//			System.out.println("新增成功!!");
//		} else {
//			System.out.println("新增失敗!!");
//		}

		// 刪除資料
//		int deleteRow = dao.delete(100000015);
//		if (deleteRow >= 1) {
//			System.out.println("刪除成功!!");
//		} else {
//			System.out.println("刪除失敗!!");
//		}

		// 修改資料
//		User user2 = new User();
//		AcctState acctState = new AcctState();
//		acctState.setAcctStateId(1);

//		AcctType acctType = new AcctType();
//		acctType.setAcctTypeId(3);
//		user2.setUserId(100000014);
//		user2.setUserAcct("a12wq131@gmail.com");
//		user2.setUserPwd("qwer1234");
//		user2.setUserName("易吉小壞貓");
//		user2.setUserNickName("超大派");
//		user2.setBirthday(java.sql.Date.valueOf("2020-11-21"));
//		user2.setAddress("台北市內湖區港前路一段xx號xx樓");
//		user2.setPhoneNum("0917383662");
//		user2.setGender(false);
//		user2.setUserPwdErrTimes(2);
//		user2.setAcctState(acctState);
//		user2.setAcctType(acctType);

//		int updateRow = dao.update(user2);
//		if (updateRow >= 1) {
//			System.out.println("修改成功!!");
//		} else {
//			System.out.println("修改失敗!!");
//		}

		// 查詢資料(單筆)，搭配fetch=FetchType.EAGER
//		User user3 = dao.findByPK(100000014);
//		System.out.println(user3.getUserId());
//		System.out.println(user3.getUserAcct());
//		System.out.println(user3.getUserCreateTime());
//		System.out.println(user3.getAcctState().getAcctStateType());
//		System.out.println(user3);

		// 查詢資料(多筆)，搭配fetch=FetchType.EAGER
//		List<User> list = dao.getAll();
//		for (User user : list) {
//			System.out.println(user);
//		}
		
		//透過會員暱稱查詢
		User user4 = dao.findUserByUserNickname("大憨吉吉");
		System.out.println(user4);
	}
}
