package com.petlife.shelter.service;

import java.util.List;
import java.util.Map;

import com.petlife.shelter.entity.Shelter;

public interface ShelterService {

	Shelter addShelter(Shelter shelter);

	Shelter updateShelter(Shelter shelter);

//	void deleteShelter(Integer shelter);

	Shelter getShelterByShelterId(Integer shelterId);

	List<Shelter> getAllShelters(int currentPage);

	int getPageTotal();

	List<Shelter> getSheltersByCompositeQuery(Map<String, String[]> map);

	boolean existShelterAccount(String shelterAcct);

	// 會員登入
	Map<String, Integer> shelterLogin(String shelterAcct, String shelterPwd);
	
	String getNewPwd(String shelterAcct);
}
