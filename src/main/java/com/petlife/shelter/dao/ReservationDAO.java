package com.petlife.shelter.dao;

import java.util.List;
import java.util.Map;

import com.petlife.shelter.entity.Reservation;

public interface ReservationDAO {
	
	int insert(Reservation entity);
	
	Reservation update(Reservation entity);
	
	int delete(Integer id);
	
	Reservation getById(Integer id);
	Reservation getByResType(Integer resType);
	Reservation getByShelterBookingId(Integer shelterBookingId);
	Reservation getByPetId(Integer petId);
	
	List<Reservation> getAll();
	
	List<Reservation> getAll(Integer userId);
	
	List<Reservation> getResByCompositeQuery(Map<String, String> map);
	
	long getTotal();
	
}
