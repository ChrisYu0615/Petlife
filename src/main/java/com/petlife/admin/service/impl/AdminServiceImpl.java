package com.petlife.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.petlife.admin.dao.AdminDAO;
import com.petlife.admin.dao.impl.AdminDAOImpl;
import com.petlife.admin.entity.AcctState;
import com.petlife.admin.entity.Admin;
import com.petlife.admin.service.AdminService;
import com.petlife.user.entity.User;
import com.petlife.util.MailService;
import com.petlife.util.RandomPassword;

public class AdminServiceImpl implements AdminService {
	private AdminDAO dao;

	public AdminServiceImpl() {
		dao = new AdminDAOImpl();
	}

	@Override
	public Admin addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteAdmin(Integer adminId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin getAdminByAdminId(Integer adminId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> getAllAdmins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exisAdminAccount(String adminAcct) {
		if (dao.findByAdminAccount(adminAcct) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Integer> adminLogin(String adminAcct, String adminPwd) {
		Map<String, Integer> loginStatus = new HashMap<>();
		Admin admin = dao.findByAdminAccountAndPassword(adminAcct, adminPwd);
		if (admin != null) {
			// 登入成功，取得該帳號的帳號狀態
			Integer adminAcctState = admin.getAcctState().getAcctStateId();
			loginStatus.put("acctState", adminAcctState);
			if (adminAcctState == 0) {
				// 帳號狀態為0代表該帳號處於可使用狀態，將密碼錯誤次數更新為0
				// 並取得該管理員ID
				admin.setAdminPwdErrTimes(0);
				dao.update(admin);
				loginStatus.put("adminId", admin.getAdminId());
			}
		} else {
			// 登入失敗，表示密碼錯誤，更新管理員密碼錯誤次數
			admin = dao.findByAdminAccount(adminAcct);
			Integer pwdErrTimes = admin.getAdminPwdErrTimes() + 1;
			admin.setAdminPwdErrTimes(pwdErrTimes);
			// 判斷管理員目前錯誤次數是否超過或等於5次，如果是就更新管理員狀態
			if (pwdErrTimes >= 5) {
				AcctState acctState = new AcctState(2, "密碼錯誤多次");
				admin.setAcctState(acctState);
				dao.update(admin);
				loginStatus.put("acctState", admin.getAcctState().getAcctStateId());
			} else {
				// 如果錯誤次數未達5次，就更新密碼錯誤次數
				dao.update(admin);
				loginStatus.put("acctState", 5);
			}
		}
		return loginStatus;
	}
	
	@Override
	public String getNewPwd(String adminAcct) {
		Admin admin = dao.findByAdminAccount(adminAcct);
		Integer acctStateId = admin.getAcctState().getAcctStateId();
		if (acctStateId == 0 || acctStateId == 2) {
			String newPassword = RandomPassword.getNewPassword();
			admin.setAdminPwd(newPassword);
			admin.setAcctState(new AcctState(0, "可使用"));
			admin.setAdminPwdErrTimes(0);
			dao.update(admin);
			// 寄信表示變更成功
			MailService.getNewPassword(adminAcct, newPassword);
			return "密碼變更成功!!請至您的信箱查看";
		}
		return "帳號處於停權或未審核狀態，請和管理員聯繫!!";
	}

}
