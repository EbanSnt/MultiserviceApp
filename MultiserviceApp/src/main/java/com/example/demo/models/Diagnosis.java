package com.example.demo.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="diagnosis")
public class Diagnosis {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date diagnosisDate;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="product_id")
	private ProductReceipt product;
	
	@NotEmpty
	private String diagnosisDetails;
	
	@Positive
	private int repairPrice;
	
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

	public Diagnosis() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDiagnosisDate() {
		return diagnosisDate;
	}

	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

	public ProductReceipt getProduct() {
		return product;
	}

	public void setProduct(ProductReceipt product) {
		this.product = product;
	}

	public String getDiagnosisDetails() {
		return diagnosisDetails;
	}

	public void setDiagnosisDetails(String diagnosisDetails) {
		this.diagnosisDetails = diagnosisDetails;
	}

	public int getRepairPrice() {
		return repairPrice;
	}

	public void setRepairPrice(int repairPrice) {
		this.repairPrice = repairPrice;
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
