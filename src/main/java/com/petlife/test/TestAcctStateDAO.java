package com.petlife.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.petlife.acctstate.dao.AcctStateDAO;
import com.petlife.acctstate.dao.AcctStateDAOImpl;
import com.petlife.acctstate.entity.AcctState;
import com.petlife.user.entity.User;

public class TestAcctStateDAO {
	public static void main(String[] args) {
		AcctStateDAO dao = new AcctStateDAOImpl();

		// 新增資料
//		AcctState acctState = new AcctState();
//		acctState.setAcctStateId(5);
//		acctState.setAcctStateType("測試新增1");
//		acctState.setUsers(null);
//		int addRow = dao.add(acctState);
//		if (addRow >= 1) {
//			System.out.println("新增成功!!");
//		} else {
//			System.out.println("新增失敗!!");
//		}

		// 刪除資料
//		int deleteRow = dao.delete(5);
//		if (deleteRow >= 1) {
//			System.out.println("刪除成功!!");
//		} else {
//			System.out.println("刪除失敗!!");
//		}

		// 修改資料
//		AcctState acctState2 = new AcctState();
//		acctState2.setAcctStateId(5);
//		acctState2.setAcctStateType("測試更新1");
//		int updateRow = dao.update(acctState2);
//		if (updateRow >= 1) {
//			System.out.println("修改成功!!");
//		} else {
//			System.out.println("修改失敗!!");
//		}

		// 查詢資料(單筆)，搭配fetch=FetchType.EAGER
//		AcctState acctState3 = dao.findByPK(5);
//		System.out.println(acctState3.getAcctStateId());
//		System.out.println(acctState3.getAcctStateType());
//		System.out.println(acctState3.getUsers());

		// 查詢資料(多筆)，搭配fetch=FetchType.EAGER
		List<AcctState> list = dao.getAll();
		for(AcctState acctState : list) {
			Set<User> users = acctState.getUsers();
			for(User user : users) {
				System.out.println(user.getUserId());
			}
		}
	}
}
