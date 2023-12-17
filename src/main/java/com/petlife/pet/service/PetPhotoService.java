package com.petlife.pet.service;

import java.util.List;
import java.util.Map;

import com.petlife.pet.entity.PetPhoto;
import com.petlife.pet.entity.PetVariety;

public interface PetPhotoService {
	PetPhoto addPetPhoto(PetPhoto Petphoto) throws Exception;
	
	PetPhoto getOnePet(Integer id) ;
	
//	void deletePetPhoto(Integer id);
//	
//	PetVariety updatePetVariety(PetVariety petVariety);
//	
//	List<PetVariety> getAll();
//	
//	List<PetVariety> getByCompositeQuery(Map<String, String[]> map);
//	
//	PetVariety getOnePetVariety(Integer id) ;
//	
//	PetVariety update_putPetVariety (PetVariety petVariety);
}
