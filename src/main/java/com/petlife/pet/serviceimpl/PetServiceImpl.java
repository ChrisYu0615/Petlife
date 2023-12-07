package com.petlife.pet.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.petlife.pet.dao.impl.PetDAOImpl;
import com.petlife.pet.entity.Pet;
import com.petlife.pet.service.PetService;
import com.petlife.util.Idao;



public class PetServiceImpl implements PetService {
	private Idao<Pet> dao;

	public PetServiceImpl() {
		dao = new PetDAOImpl();
	}

	
	public Pet  addPet(Pet pet) {
		dao.insert(pet);
		System.out.println("yes");
		return pet;
	}
	@Override
	public void deletePet(Integer id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Pet updatePet(Pet pet) {
		// TODO Auto-generated method stub
		System.out.println("2");
		return dao.update(pet);
	}
	@Override
	public List<Pet> getAll() {
		return dao.getAll();
	}
	@Override
	public List<Pet> getByCompositeQuery(Map<String, String[]> map) {
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
	public Pet getOnePet(Integer id) {
		return dao.getById(id);
	}
	@Override
	public Pet update_putPet(Pet pet) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	

}
