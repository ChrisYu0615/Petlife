package com.petlife.user.service.impl;

import java.util.List;

import com.petlife.admin.entity.AcctState;
import com.petlife.user.dao.UserDAO;
import com.petlife.user.dao.impl.UserDAOImpl2;
import com.petlife.user.entity.User;
import com.petlife.user.service.UserServeice;

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
	public int getPageTotal() {
		return 0;
	}

	@Override
	public boolean existUserNickname(String userNickName) {
		if (dao.findUserByUserNickname(userNickName) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean exisUserAccount(String userAcct) {
		if (dao.findUserByUserAccount(userAcct) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Integer userLogin(String userAcct, String userPwd) {
		User user = dao.findUserByUserAccountAndPassword(userAcct, userPwd);
		if (user != null) {
			// 登入成功(帳密一樣)，回傳帳號狀態
			Integer userAcctState = user.getAcctState().getAcctStateId();
			if (userAcctState == 0) {
				user.setUserPwdErrTimes(0);
				dao.update(user);
				return user.getUserId();
			}
			return userAcctState;
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
				return user.getAcctState().getAcctStateId();
				// 如果錯誤次數未達5次，就更新密碼錯誤次數
			} else {
				dao.update(user);
				return 3;
			}
		}
	}
}
