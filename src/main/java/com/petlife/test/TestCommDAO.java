package com.petlife.test;

import java.util.List;

import javax.swing.undo.AbstractUndoableEdit;

import com.petlife.comm.dao.CommDAOImpl;
import com.petlife.comm.entity.Comm;
import com.petlife.user.dao.UserDAOImpl2;
import com.petlife.user.entity.User;

public class TestCommDAO {
	public static void main(String[] args) {
		CommDAOImpl dao = new CommDAOImpl();
		// 撈出userId=100000001
		
//		UserDAOImpl2 UserDAO = new UserDAOImpl2();
//		int userId = 100000001;
//		User user = UserDAO.findByPK(userId);
		
		// 新增
//		Comm comm = new Comm();
//		comm.setSellerId(200000001);
//		comm.setCommName("TEST COMM-1");
//		comm.setCommDesc("TEST CommDesc");
//		comm.setCommState(0);
//		comm.setListDatetime(new java.sql.Timestamp(System.currentTimeMillis()));
//		comm.setCommCatId(2000);
//		comm.setCommStock(200000);
//		comm.setCommPrice(100);
//		comm.setCommOnsalePrice(90);
//		comm.setCommViewCount(50);	
//		dao.add(comm);
		
		// 刪除
//		int deletedPk = 19;
//		int result = dao.delete(deletedPk);
//		if(result == 1) {
//			System.out.println("成功刪除: " + deletedPk);
//		} else {
//			System.out.println("刪除失敗");
//		}
		// 更改
//		int updatePk = 20;
//		Comm comm1 = new Comm();
//		comm1.setCommId(updatePk);
//		comm1.setSellerId(200000002);
//		comm1.setCommName("test update");
//		comm1.setCommDesc("test update");
//		comm1.setCommState(3);
//		comm1.setCommCatId(2000);
//		comm1.setCommStock(20);
//		comm1.setCommPrice(2000);
//		comm1.setCommOnsalePrice(1800);
//		comm1.setCommViewCount(20);
//		
//		int result1 = dao.update(comm1);
//		if(result1 == 1) {
//			System.out.println("成功更新");
//		} else {
//			System.out.println("失敗更新");
//		}
		// 查詢
//		Comm result2 = dao.findByPk(20);
//		System.out.println("找到的comm name: " + result2.getCommName());
		
		// 找多個
		List<Comm> resultList = dao.getAll();
		for(Comm comm : resultList) {
			System.out.println("Id = " + comm.getCommId());
		}
	}
}
