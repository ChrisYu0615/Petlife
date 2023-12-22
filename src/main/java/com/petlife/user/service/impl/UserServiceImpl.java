package com.petlife.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.petlife.admin.entity.AcctState;
import com.petlife.user.dao.UserDAO;
import com.petlife.user.dao.impl.UserDAOImpl2;
import com.petlife.user.entity.User;
import com.petlife.user.service.UserServeice;
import com.petlife.util.MailService;
import com.petlife.util.RandomPassword;

public class UserServiceImpl implements UserServeice {
	private UserDAO dao;

	public UserServiceImpl() {
		dao = new UserDAOImpl2();
	}

	@Override
	public User addUser(User user) {
		Integer id = dao.add(user);
		user = dao.findByPK(id);
		return user;
	}

	@Override
	public Integer deleteUser(Integer userId) {
		return dao.delete(userId);
	}

	@Override
	public Integer updateUser(User user) {
		return dao.update(user);
	}

	@Override
	public User getUserByUserId(Integer userId) {
		return dao.findByPK(userId);
	}

	@Override
	public List<User> getAllUsers() {
		return dao.getAll();
	}

	@Override
	public boolean exisUserAccount(String userAcct) {
		if (dao.findUserByUserAccount(userAcct) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Integer> userLogin(String userAcct, String userPwd) {
		Map<String, Integer> loginStatus = new HashMap<>();
		User user = dao.findUserByUserAccountAndPassword(userAcct, userPwd);
		if (user != null) {
			// 登入成功，取得該帳號的帳號狀態
			Integer userAcctState = user.getAcctState().getAcctStateId();
			loginStatus.put("acctState", userAcctState);
			if (userAcctState == 0) {
				// 帳號狀態為0代表該帳號處於可使用狀態，將密碼錯誤次數更新為0
				// 並取得該會員ID
				user.setUserPwdErrTimes(0);
				dao.update(user);
				loginStatus.put("userId", user.getUserId());
			}
		} else {
			// 登入失敗，表示密碼錯誤，更新會員密碼錯誤次數
			user = dao.findUserByUserAccount(userAcct);
			Integer pwdErrTimes = user.getUserPwdErrTimes() + 1;
			user.setUserPwdErrTimes(pwdErrTimes);
			// 判斷會員目前錯誤次數是否超過或等於5次，如果是就更新會員狀態
			if (pwdErrTimes >= 5) {
				AcctState acctState = new AcctState(2, "密碼錯誤多次");
				user.setAcctState(acctState);
				dao.update(user);
				loginStatus.put("acctState", user.getAcctState().getAcctStateId());
				// 如果錯誤次數未達5次，就更新密碼錯誤次數
			} else {
				dao.update(user);
				loginStatus.put("acctState", 5);
			}
		}
		return loginStatus;
	}

	@Override
	public String getNewPwd(String userAcct) {
		User user = dao.findUserByUserAccount(userAcct);
		Integer acctStateId = user.getAcctState().getAcctStateId();
		if (acctStateId == 0 || acctStateId == 2) {
			String newPassword = RandomPassword.getNewPassword();
			user.setUserPwd(newPassword);
			user.setAcctState(new AcctState(0, "可使用"));
			user.setUserPwdErrTimes(0);
			dao.update(user);
			// 寄信表示變更成功
			Thread thread = new Thread(() -> {
				MailService.getNewPassword(userAcct, newPassword);
			});
			thread.start();
			return "密碼變更成功!!請至您的信箱查看";
		}
		return "帳號處於停權或未審核狀態，請和管理員聯繫!!";
	}
}
