package com.petlife.shelter.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.petlife.shelter.dao.ReservationDAO;
import com.petlife.shelter.dao.impl.ReservationDAOImpl;
import com.petlife.shelter.entity.Reservation;
import com.petlife.shelter.service.ReservationService;

public class ReservationServiceImpl implements ReservationService {
	
	private ReservationDAO dao;
	
	public ReservationServiceImpl() {
		dao = new ReservationDAOImpl();
	}

	@Override
	public Reservation addRes(Reservation res) {
		dao.insert(res);
		return res;
	}

	@Override
	public Reservation updateRes(Reservation res) {
		dao.update(res);
		return res;
	}

	@Override
	public void deleteRes(Integer resId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reservation getResByResType(Integer resId) {
		return dao.getById(resId);
	}

	@Override
	public List<Reservation> getAll() {
		return dao.getAll();
	}
	
	@Override
	public List<Reservation> getAll(Integer userId) {
		return dao.getAll(userId);
	}

	@Override
	public List<Reservation> getResByCompositeQuery(Map<String, String[]> map) {
		System.out.println("2複合查詢Service");
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
		
		return dao.getResByCompositeQuery(query);
		
	}

	@Override
	public Reservation getResByResId(Integer resId) {
	
		return dao.getById(resId);
	}
	
	public List<Reservation> getResByShelterBookingId(Integer shelterBookingId) {
		return dao.getByShelterBookingId(shelterBookingId);
	}
	
}
