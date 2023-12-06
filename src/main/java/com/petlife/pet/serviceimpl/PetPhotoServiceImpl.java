package com.petlife.pet.serviceimpl;

import com.petlife.pet.dao.impl.PetPhotoDAOImpl;
import com.petlife.pet.entity.PetPhoto;
import com.petlife.pet.service.PetPhotoService;
import com.petlife.util.Idao;

public class PetPhotoServiceImpl implements PetPhotoService {
	private Idao<PetPhoto> dao;

	public PetPhotoServiceImpl() {
		dao = new PetPhotoDAOImpl();

	}
	public PetPhoto addPetPhoto(PetPhoto Petphoto)  {
//		return dao.insert(petVariety);
		// check
		
		dao.insert(Petphoto);
		System.out.println("yes");
		return Petphoto;
	}

	

}
