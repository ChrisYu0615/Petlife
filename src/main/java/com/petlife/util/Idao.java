package com.petlife.util;

import java.util.List;
import java.util.Map;

public interface Idao<T> {
	
	int insert(T entity);
	
	T update(T entity);
	
	void delete(Integer id);
	
	T getById(Integer id);
	
	List<T> getAll();
	
	List<T> getByCompositeQuery(Map<String, String> map);
}
