package com.petlife.pet.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;





@Entity
@Table(name = "petvariety")
public class PetVariety {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pet_variety_id", updatable = false)
	@Expose
	private Integer id;

	@Convert(converter = Converter.class)
	@Column(name = "pet_type")
	@Expose
	private String type;

	@Column(name = "pet_variety")
	@Expose
	private String variety;
	
	@OneToMany(mappedBy = "variety", cascade = CascadeType.ALL)
	private List<Pet> pets=new ArrayList<Pet>();
	

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

}
