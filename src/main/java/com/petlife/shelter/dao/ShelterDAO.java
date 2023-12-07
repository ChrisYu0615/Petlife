package com.petlife.shelter.dao;

import java.util.List;
import java.util.Map;

import com.petlife.shelter.entity.Shelter;

public interface ShelterDAO {

	int insert(Shelter entity);
	
	int update(Shelter entity);
	
	int delete(Integer id);
	
	Shelter getById(Integer id);	
	Shelter getByName(String shelterName);
	Shelter getByAddress(String shelterAddress);
	Shelter getByAccount(String shelterAcct);
	
	List<Shelter> getAll();
	
	List<Shelter> getByCompositeQuery(Map<String, String> map);
	
	List<Shelter> getAll(int currentPage);
	
	long getTotal();
}
