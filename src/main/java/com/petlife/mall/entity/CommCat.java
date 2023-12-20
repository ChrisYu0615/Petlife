package com.petlife.mall.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "comm_cat")
public class CommCat {
	@Id
	@Column(name = "comm_cat_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer commCatId;
	
	@Column(name = "comm_main_sort")
	@Expose
	private String commMainSort;
	
	@Column(name = "comm_major_sort")
	@Expose
	private String commMajorSort;
	
	public CommCat() {
	}

	public Integer getCommCatId() {
		return commCatId;
	}

	public void setCommCatId(Integer commCatId) {
		this.commCatId = commCatId;
	}

	public String getCommMainSort() {
		return commMainSort;
	}

	public void setCommMainSort(String commMainSort) {
		this.commMainSort = commMainSort;
	}

	public String getCommMajorSort() {
		return commMajorSort;
	}

	public void setCommMajorSort(String commMajorSort) {
		this.commMajorSort = commMajorSort;
	}

	@Override
	public int hashCode() {
		return Objects.hash(commCatId, commMainSort, commMajorSort);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommCat other = (CommCat) obj;
		return Objects.equals(commCatId, other.commCatId) && Objects.equals(commMainSort, other.commMainSort)
				&& Objects.equals(commMajorSort, other.commMajorSort);
	}

	@Override
	public String toString() {
		return "CommCat [commCatId=" + commCatId + ", commMainSort=" + commMainSort + ", commMajorSort=" + commMajorSort
				+ "]";
	}
}
