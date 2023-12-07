package com.petlife.pet.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.petlife.pet.dao.impl.PetVarietyDAOImpl;
import com.petlife.pet.entity.PetVariety;
import com.petlife.pet.service.CalException;
import com.petlife.pet.service.PetVarietyService;
import com.petlife.util.Idao;



public class PetVarietyServiceImpl implements PetVarietyService {

	private Idao<PetVariety> dao;

	public PetVarietyServiceImpl() {
		dao = new PetVarietyDAOImpl();

	}

	public PetVariety addPetVariety(PetVariety petVariety) throws CalException {
//		return dao.insert(petVariety);
		// check
		
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("variety", new String[] { petVariety.getVariety() });
		var petVarietyList = getByCompositeQuery(map);
		if (petVarietyList.size() == 0) {
			dao.insert(petVariety);
		}else {
			
			throw new CalException("重複");
		}
		return petVariety ;

	}

	public PetVariety updatePetVariety(PetVariety petVariety) {

		return petVariety;
	}

	public PetVariety update_putPetVariety(PetVariety petVariety) {

		return dao.update(petVariety);
	}

	@Override
	public void deletePetVariety(Integer id) {
		// TODO Auto-generated method stub

	}

	public List<PetVariety> getAll() {
		return dao.getAll();
	}

	public List<PetVariety> getByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();
		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();

		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0]; // getValue拿到一個String陣列, 接著[0]取得第一個元素檢查
			if (value == null || value.isEmpty()) {
				continue;
			}
			query.put(key, value);
		}

		System.out.println(query);

		return dao.getByCompositeQuery(query);
	}

	public PetVariety getOnePetVariety(Integer id) {
		return dao.getById(id);
	}

}
