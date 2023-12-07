package com.petlife.pet.entity;


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



@Entity
@Table(name = "petvariety")
public class PetVariety {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pet_variety_id", updatable = false)
	private Integer id;

	@Convert(converter = Converter.class)
	@Column(name = "pet_type")
	private String type;

	@Column(name = "pet_variety")
	private String variety;
	
	//@OneToMany(mappedBy = "variety", cascade = CascadeType.ALL)
	//private Set<Pet> pets;
	
//	public Set<Pet> getPets() {
//		return pets;
//	}
//
//	public void setPets(Set<Pet> pets) {
//		this.pets = pets;
//	}

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
