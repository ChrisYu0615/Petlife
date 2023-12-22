package com.petlife.shelter.service;


import java.util.List;
import java.util.Map;

import com.petlife.shelter.entity.ShelterBooking;







public interface ShelterBookingService {
	
	ShelterBooking addShelterBooking(ShelterBooking ShelterBooking) ;
	

	
	ShelterBooking updateShelterBooking(ShelterBooking ShelterBooking);
	
	List<ShelterBooking> getAll();
	
	List<ShelterBooking> getByCompositeQuery(Map<String, String[]> map);
	
	ShelterBooking getOneShelterBooking(Integer id) ;
	
	

}
