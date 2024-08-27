package com.example.demo.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="spare_parts")
public class SparePart {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty
	private String sparePartName;
	
	private String sparePartDetails;
	
	private int sparePartQuantity;
	
	@NotEmpty
	private String sparePartType;
	
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

	public SparePart() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSparePartName() {
		return sparePartName;
	}

	public void setSparePartName(String sparePartName) {
		this.sparePartName = sparePartName;
	}

	public String getSparePartDetails() {
		return sparePartDetails;
	}

	public void setSparePartDetails(String sparePartDetails) {
		this.sparePartDetails = sparePartDetails;
	}

	public int getSparePartQuantity() {
		return sparePartQuantity;
	}

	public void setSparePartQuantity(int sparePartQuantity) {
		this.sparePartQuantity = sparePartQuantity;
	}

	public String getSparePartType() {
		return sparePartType;
	}

	public void setSparePartType(String sparePartType) {
		this.sparePartType = sparePartType;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
	//automatization
	@PrePersist 
		protected void onCreate() {
			this.createdAt = new Date(); 
		}
		
		
	@PreUpdate 
		protected void onUpdate() {
			this.updatedAt = new Date(); 
		}
}
