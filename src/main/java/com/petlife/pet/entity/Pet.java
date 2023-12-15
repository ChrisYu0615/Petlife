package com.petlife.pet.entity;



import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.petlife.shelter.entity.Reservation;
import com.petlife.shelter.entity.Shelter;


@Entity
@Table(name = "pet")
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pet_id", updatable = false)
	@Expose
	private Integer id;

//	@ManyToOne
//	@JoinColumn(name = "shelter_id", referencedColumnName = "shelter_id",insertable = false)
//	private Shelter shelterId
	@Column(name = "shelter_id")
	@Expose
	private Integer shelterId;

	@Column(name = "pet_gender")
	@Convert(converter = GenderConverter.class)
	@Expose
	private String petGender;

	@Column(name = "pet_variety_id")
	@Expose
	private Integer petVariety;

	@Column(name = "pet_ligation")
	@Convert(converter = PetLigationConverter.class)
	@Expose
	private String petLigation;


	@Column(name = "pet_color")
	@Expose
	private String petColor;

	@Column(name = "adopt")
	@Expose
	private Boolean adopt;

	@Column(name = "pet_content")
	@Expose
	private String petContent;

	@Column(name = "come_in_date")
	@Expose
	private Date comeInDate;

	@Column(name = "pet_cage")
	@Expose
	private String petCage;

	@Column(name = "pet_num")
	@Expose
	private String petNum;

	@Column(name = "adopted")
	@Expose
	private Boolean adopted;

	@Column(name = "user_id")
	@Expose
	private String userId;

	@Column(name = "adopt_date")
	@Expose
	private Date adoptDate;
	

	@Column(name = "pet_keyin_date", updatable = false, insertable = false)
	@Expose
	private Timestamp petKeyinDate;
	
	@ManyToOne
	@JoinColumn(name = "pet_variety_id", referencedColumnName = "pet_variety_id",updatable = false, insertable = false)
	@Expose
	private PetVariety variety;
	
	@ManyToOne
	@JoinColumn(name = "shelter_id", referencedColumnName = "shelter_id",updatable = false, insertable = false)
	@Expose
	private Shelter Sheltername;


	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	@OrderBy("pet_photo_id asc")
	@Expose
	private List<PetPhoto> petPhotos = new ArrayList<PetPhoto>();
	

	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	private Set<Reservation> reservations;


	public Pet(Integer shelterId, String petGender, Integer petVariety, String petLigation, String petColor,
			Boolean adopt, String petContent, Date comeInDate, String petCage, String petNum, Boolean adopted,
			String userId, Date adoptDate) {
		super();
		this.shelterId = shelterId;
		this.petGender = petGender;
		this.petVariety = petVariety;
		this.petLigation = petLigation;
		this.petColor = petColor;
		this.adopt = adopt;
		this.petContent = petContent;
		this.comeInDate = comeInDate;
		this.petCage = petCage;
		this.petNum = petNum;
		this.adopted = adopted;
		this.userId = userId;
		this.adoptDate = adoptDate;
	}

	public Pet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pet(Integer id, Integer shelterId, String petGender, Integer petVariety, String petLigation, String petColor,
			Boolean adopt, String petContent, Date comeInDate, String petCage, String petNum, Boolean adopted,
			String userId, Date adoptDate) {
		super();
		this.id = id;
		this.shelterId = shelterId;
		this.petGender = petGender;
		this.petVariety = petVariety;
		this.petLigation = petLigation;
		this.petColor = petColor;
		this.adopt = adopt;
		this.petContent = petContent;
		this.comeInDate = comeInDate;
		this.petCage = petCage;
		this.petNum = petNum;
		this.adopted = adopted;
		this.userId = userId;
		this.adoptDate = adoptDate;
	}

	public Shelter getSheltername() {
		return Sheltername;
	}

	public void setSheltername(Shelter sheltername) {
		Sheltername = sheltername;
	}

	public PetVariety getVariety() {
		return variety;
	}

	public void setVariety(PetVariety variety) {
		this.variety = variety;
	}


	
	public Integer getPetVariety() {
		return petVariety;
	}

	public void setPetVariety(Integer petVariety) {
		this.petVariety = petVariety;
	}

	public Integer getId() {
		return id;
	}

	public List<PetPhoto> getPetPhotos() {
		return petPhotos;
	}

	public void setPetPhotos(List<PetPhoto> petPhotos) {
		this.petPhotos = petPhotos;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShelterId() {
		return shelterId;
	}

	public void setShelterId(Integer shelterId) {
		this.shelterId = shelterId;
	}

	public String getPetGender() {
		return petGender;
	}

	public void setPetGender(String petGender) {
		this.petGender = petGender;
	}

	public String getPetLigation() {
		return petLigation;
	}

	public void setPetLigation(String petLigation) {
		this.petLigation = petLigation;
	}

	public String getPetColor() {
		return petColor;
	}

	public void setPetColor(String petColor) {
		this.petColor = petColor;
	}

	public Boolean getAdopt() {
		return adopt;
	}

	public void setAdopt(Boolean adopt) {
		this.adopt = adopt;
	}

	public String getPetContent() {
		return petContent;
	}

	public void setPetContent(String petContent) {
		this.petContent = petContent;
	}

	public Date getComeInDate() {
		return comeInDate;
	}

	public void setComeInDate(Date comeInDate) {
		this.comeInDate = comeInDate;
	}

	public String getPetCage() {
		return petCage;
	}

	public void setPetCage(String petCage) {
		this.petCage = petCage;
	}

	public String getPetNum() {
		return petNum;
	}

	public void setPetNum(String petNum) {
		this.petNum = petNum;
	}

	public Boolean getAdopted() {
		return adopted;
	}

	public void setAdopted(Boolean adopted) {
		this.adopted = adopted;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getAdoptDate() {
		return adoptDate;
	}

	public void setAdoptDate(Date adoptDate) {
		this.adoptDate = adoptDate;
	}
	public Timestamp getPetKeyinDate() {
		return petKeyinDate;
	}


	public void setPetKeyinDate(Timestamp petKeyinDate) {
		this.petKeyinDate = petKeyinDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		return Objects.equals(id, other.id);
	}


}

