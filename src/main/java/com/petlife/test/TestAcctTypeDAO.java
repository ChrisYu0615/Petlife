package com.petlife.test;

import java.util.List;

import com.petlife.acctstate.entity.AcctState;
import com.petlife.accttype.dao.AcctTypeDAO;
import com.petlife.accttype.dao.AcctTypeDAOImpl;
import com.petlife.accttype.entity.AcctType;

public class TestAcctTypeDAO {
	public static void main(String[] args) {
		AcctTypeDAO dao = new AcctTypeDAOImpl();
		
		
		// 新增資料
//		AcctType acctType = new AcctType();
//		acctType.setAcctTypeId(4);
//		acctType.setAcctType("測試新增1");
//		acctType.setUsers(null);
//		int addRow = dao.add(acctType);
//		if (addRow >= 1) {
//			System.out.println("新增成功!!");
//		} else {
//			System.out.println("新增失敗!!");
//		}

		// 刪除資料
//		int deleteRow = dao.delete(4);
//		if (deleteRow >= 1) {
//			System.out.println("刪除成功!!");
//		} else {
//			System.out.println("刪除失敗!!");
//		}

		// 修改資料
//		AcctType acctType2 = new AcctType();
//		acctType2.setAcctTypeId(4);
//		acctType2.setAcctType("測試更新1");
//		int updateRow = dao.update(acctType2);
//		if (updateRow >= 1) {
//			System.out.println("修改成功!!");
//		} else {
//			System.out.println("修改失敗!!");
//		}

		// 查詢資料(單筆)
//		AcctType acctType3 = dao.findByPK(4);
//		System.out.println(acctType3.getAcctTypeId());
//		System.out.println(acctType3.getAcctType());
//		System.out.println(acctType3);

		// 查詢資料(多筆)
		List<AcctType> list = dao.getAll();
		for(AcctType acctType : list) {
			System.out.println(acctType);
		}
	}
}
