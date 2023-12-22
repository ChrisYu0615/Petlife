package com.petlife.shelter.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.petlife.shelter.dao.impl.ShelterBookingDAOImpl;
import com.petlife.shelter.entity.ShelterBooking;
import com.petlife.shelter.service.ShelterBookingService;
import com.petlife.util.Idao;


public class ShelterBookingServiceImpl implements ShelterBookingService{
	private Idao<ShelterBooking> dao;
	
	public ShelterBookingServiceImpl() {
		dao = new ShelterBookingDAOImpl();
	}
	@Override
	public ShelterBooking addShelterBooking(ShelterBooking ShelterBooking)  {
		System.out.println("ShelterBookingServiceImpl : addShelterBooking Entry");
		dao.insert(ShelterBooking);
		return ShelterBooking;
	}

	@Override
	public ShelterBooking updateShelterBooking(ShelterBooking ShelterBooking) {
		System.out.println("ShelterBookingServiceImpl : updateShelterBooking Entry");
		return dao.update(ShelterBooking);
	}

	@Override
	public List<ShelterBooking> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShelterBooking> getByCompositeQuery(Map<String, String[]> map) {
		Map <String , String> query = new HashMap<>();
		Set<Map.Entry<String,String[]>> entry= map.entrySet();
		
		for(Map.Entry<String, String[]>row:entry) {
			
			String key = row.getKey();
			if("action".equals(key)) {
				continue;
			}
			String value = row.getValue()[0];
			if(value == null|| value.isEmpty()) {
				continue;
			}
			query.put(key, value);
			
		}
		
		return dao.getByCompositeQuery(query);
	}

	@Override
	public ShelterBooking getOneShelterBooking(Integer id) {
		return dao.getById(id);
		
	}

	
}
