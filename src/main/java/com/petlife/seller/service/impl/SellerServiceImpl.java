package com.petlife.seller.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.petlife.admin.entity.AcctState;
import com.petlife.seller.dao.SellerDAO;
import com.petlife.seller.dao.SellerDAOImpl;
import com.petlife.seller.entity.Seller;
import com.petlife.seller.service.SellerService;
import com.petlife.user.entity.User;
import com.petlife.util.MailService;
import com.petlife.util.RandomPassword;

public class SellerServiceImpl implements SellerService {
	private SellerDAO dao;

	public SellerServiceImpl() {
		dao = new SellerDAOImpl();
	}

	@Override
	public Seller addSeller(Seller seller) {
		Integer id = dao.add(seller);
		seller = dao.findByPK(id);
		return seller;
	}

	@Override
	public Integer deleteSeller(Integer sellerId) {
		return dao.delete(sellerId);
	}

	@Override
	public Integer updateSeller(Seller seller) {
		return dao.update(seller);
	}

	@Override
	public Seller getSellerBySellerId(Integer sellerId) {
		return dao.findByPK(sellerId);
	}

	@Override
	public List<Seller> getAllSellers(String... conditions) {
		return dao.getAll(conditions);
	}

	// 查詢賣家帳號是否重複
	@Override
	public boolean existSellerAccount(String sellerAccount) {
		if (dao.findSellerBySellerAccount(sellerAccount) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Integer> sellerLogin(String sellerAcct, String sellerPwd) {
		Map<String, Integer> loginStatus = new HashMap<>();
		Seller seller = dao.findSellerBySellerAccountAndPassword(sellerAcct, sellerPwd);
		if (seller != null) {
			// 登入成功，取得該帳號的帳號狀態
			Integer sellerAcctState = seller.getAcctState().getAcctStateId();
			loginStatus.put("acctState", sellerAcctState);
			if (sellerAcctState == 0) {
				// 帳號狀態為0代表該帳號處於可使用狀態，將密碼錯誤次數更新為0
				// 並取得該賣家ID
				seller.setSellerPwdErrTimes(0);
				dao.update(seller);
				loginStatus.put("sellerId", seller.getSellerId());
			}
		} else {
			// 登入失敗，表示密碼錯誤，更新會員密碼錯誤次數
			seller = dao.findSellerBySellerAccount(sellerAcct);
			Integer pwdErrTimes = seller.getSellerPwdErrTimes() + 1;
			seller.setSellerPwdErrTimes(pwdErrTimes);
			// 判斷會員目前錯誤次數是否超過或等於5次，如果是就更新會員狀態
			if (pwdErrTimes >= 5) {
				AcctState acctState = new AcctState(2, "密碼錯誤多次");
				seller.setAcctState(acctState);
				dao.update(seller);
				loginStatus.put("acctState", seller.getAcctState().getAcctStateId());
				// 如果錯誤次數未達5次，就更新密碼錯誤次數
			} else {
				dao.update(seller);
				loginStatus.put("acctState", 5);
			}
		}
		return loginStatus;
	}

	@Override
	public String getNewPwd(String sellerAcct) {
		Seller seller = dao.findSellerBySellerAccount(sellerAcct);
		Integer acctStateId = seller.getAcctState().getAcctStateId();
		if (acctStateId == 0 || acctStateId == 2) {
			String newPassword = RandomPassword.getNewPassword();
			seller.setSellerPwd(newPassword);
			seller.setAcctState(new AcctState(0, "可使用"));
			seller.setSellerPwdErrTimes(0);
			dao.update(seller);
			// 寄信表示變更成功
			Thread thread = new Thread(() -> {
				MailService.getNewPassword(sellerAcct, newPassword);
			});
			thread.start();
			return "密碼變更成功!!請至您的信箱查看";
		}
		return "帳號處於停權或未審核狀態，請和管理員聯繫!!";
	}

}
