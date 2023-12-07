package com.petlife.shelter.service.impl;

import static com.petlife.util.Constants.PAGE_MAX_RESULT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.petlife.shelter.dao.ShelterDAO;
import com.petlife.shelter.dao.impl.ShelterDAOImpl;
import com.petlife.shelter.entity.Shelter;
import com.petlife.shelter.service.ShelterService;
import com.petlife.util.HibernateUtil;

public class ShelterServiceImpl implements ShelterService{

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
		int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}


	@Override
	public List<Shelter> getSheltersByCompositeQuery(Map<String, String[]> map) {
		Map<String,String> query = new HashMap<>();
		// Map.Entry對Map集合來說就代表一組Key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for (Map.Entry<String, String[]>row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，要去除
			if ("action".equals(key)) {
				continue;	//跳過
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
	
	
	
}
