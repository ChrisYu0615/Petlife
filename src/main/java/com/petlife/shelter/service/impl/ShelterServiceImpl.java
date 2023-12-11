package com.petlife.shelter.service.impl;

import static com.petlife.util.Constants.PAGE_MAX_RESULT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.petlife.admin.entity.AcctState;
import com.petlife.seller.entity.Seller;
import com.petlife.shelter.dao.ShelterDAO;
import com.petlife.shelter.dao.impl.ShelterDAOImpl;
import com.petlife.shelter.entity.Shelter;
import com.petlife.shelter.service.ShelterService;
import com.petlife.user.entity.User;
import com.petlife.util.HibernateUtil;
import com.petlife.util.MailService;
import com.petlife.util.RandomPassword;

public class ShelterServiceImpl implements ShelterService {

	private ShelterDAO dao;

	public ShelterServiceImpl() {
		dao = new ShelterDAOImpl();
	}

	@Override
	public Shelter addShelter(Shelter shelter) {

//		Integer id = dao.insert(shelter);
//		shelter = dao.getById(id);
//		return shelter;
		dao.insert(shelter);
		return shelter;
	}

	@Override
	public Shelter updateShelter(Shelter shelter) {
		dao.update(shelter);
		System.out.println("修改2");
		return shelter;
	}

	@Override
	public Shelter getShelterByShelterId(Integer shelterId) {
		return dao.getById(shelterId);
	}

	@Override
	public List<Shelter> getAllShelters(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		// 計算Shelter數量每頁3筆的話總共有幾頁
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

	@Override
	public List<Shelter> getSheltersByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();
		// Map.Entry對Map集合來說就代表一組Key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();

		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，要去除
			if ("action".equals(key)) {
				continue; // 跳過
			}
			String value = row.getValue()[0];
			if (value == null || value.isEmpty()) {
				continue;
			}
			query.put(key, value);
		}

		System.out.println(query);

		return dao.getByCompositeQuery(query);
	}

	@Override
	public boolean existShelterAccount(String shelterAcct) {
		if (dao.getByAccount(shelterAcct) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Integer> shelterLogin(String shelterAcct, String shelterPwd) {
		Map<String, Integer> loginStatus = new HashMap<>();
		Shelter shelter = dao.getByAccountAndPassword(shelterAcct, shelterPwd);
		if (shelter != null) {
			// 登入成功，取得該帳號的帳號狀態
			Integer shelterAcctState = shelter.getAcctState().getAcctStateId();
			loginStatus.put("acctState", shelterAcctState);
			if (shelterAcctState == 0) {
				// 帳號狀態為0代表該帳號處於可使用狀態，將密碼錯誤次數更新為0
				// 並取得該收容所會員ID
				shelter.setShelterPwdErrTimes(0);
				dao.update(shelter);
				loginStatus.put("shelterId", shelter.getShelterId());
			}
		} else {
			// 登入失敗，表示密碼錯誤，更新會員密碼錯誤次數
			shelter = dao.getByAccount(shelterAcct);
			Integer pwdErrTimes = shelter.getShelterPwdErrTimes() + 1;
			shelter.setShelterPwdErrTimes(pwdErrTimes);
			// 判斷會員目前錯誤次數是否超過或等於5次，如果是就更新會員狀態
			if (pwdErrTimes >= 5) {
				AcctState acctState = new AcctState(2, "密碼錯誤多次");
				shelter.setAcctState(acctState);
				dao.update(shelter);
				loginStatus.put("acctState", shelter.getAcctState().getAcctStateId());
				// 如果錯誤次數未達5次，就更新密碼錯誤次數
			} else {
				dao.update(shelter);
				loginStatus.put("acctState", 5);
			}
		}
		return loginStatus;
	}

	@Override
	public String getNewPwd(String shelterAcct) {
		Shelter shelter = dao.getByAccount(shelterAcct);
		Integer acctStateId = shelter.getAcctState().getAcctStateId();
		if (acctStateId == 0 || acctStateId == 2) {
			String newPassword = RandomPassword.getNewPassword();
			shelter.setShelterPwd(newPassword);
			shelter.setAcctState(new AcctState(0, "可使用"));
			shelter.setShelterPwdErrTimes(0);
			dao.update(shelter);
			// 寄信表示變更成功
			MailService.getNewPassword(shelterAcct, newPassword);
			return "密碼變更成功!!請至您的信箱查看";
		}
		return "帳號處於停權或未審核狀態，請和管理員聯繫!!";
	}
}
