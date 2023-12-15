package com.petlife.pet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.google.gson.annotations.Expose;




@Entity
@Table(name = "pet_photo")
public class PetPhoto implements java.io.Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pet_photo_id", updatable = false)
	@Expose
	private Integer photoId;

	
	@Column(name = "pet_photo" ,columnDefinition = "longblob" )
	private byte[] petPhoto;
	
	@Column(name = "pet_id", updatable = false, insertable = false)
	@Expose
	private Integer petId;
	
	@ManyToOne
	//            自己的欄位名稱-> 對應到哪一個欄位
	@JoinColumn(name = "pet_id", referencedColumnName = "pet_id")
	// 代表會去dept 去找deptnoname   ,原本是EMP emp
	private Pet pet;
	

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}
	
	public byte[] getPetPhoto() {
		return petPhoto;
	}

	public void setPetPhoto(byte[] petPhoto) {
		this.petPhoto = petPhoto;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
	
	
	
	
	
}
