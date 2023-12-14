package com.petlife.shelter.dao;

import java.util.List;
import java.util.Map;

import com.petlife.shelter.entity.ResType;

public interface ResTypeDAO {
	
	int insert(ResType entity);
	
	int update(ResType entity);
	
	int delete(Integer id);
	
	ResType getById(Integer id);

	
	List<ResType> getAll();
	
	
}
