package com.petlife.shelter.service;

import java.util.List;
import java.util.Map;

import com.petlife.shelter.entity.Reservation;

public interface ReservationService {
	
	Reservation addRes(Reservation reservation);
	
	Reservation updateRes(Reservation reservation);
	
	void deleteRes(Integer resId);
	
	Reservation getResByResType(Integer resId);
	
	Reservation getResByResId(Integer resId);
	
	List<Reservation> getAll();
	
	List<Reservation> getAll(Integer userId);
	
	List<Reservation> getResByCompositeQuery(Map<String, String[]> map);
	
}
