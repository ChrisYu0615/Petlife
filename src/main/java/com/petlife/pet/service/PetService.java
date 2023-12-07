package com.petlife.pet.service;

import java.util.List;
import java.util.Map;

import com.petlife.pet.entity.Pet;



public interface PetService {
	Pet addPet(Pet pet) throws Exception;
	
	void deletePet(Integer id);
	
	Pet updatePet(Pet pet);
	
	List<Pet> getAll();
	
	List<Pet> getByCompositeQuery(Map<String, String[]> map);
	
	Pet getOnePet(Integer id) ;
	
	Pet update_putPet (Pet pet);
	
	

}
