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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="disarmaments")
public class Disarmaments {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date disarmamentDate;
	
	private String disarmamentDetails;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="product_id")
	private ProductReceipt product;
	
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

	public Disarmaments() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDisarmamentDate() {
		return disarmamentDate;
	}

	public void setDisarmamentDate(Date disarmamentDate) {
		this.disarmamentDate = disarmamentDate;
	}

	public String getDisarmamentDetails() {
		return disarmamentDetails;
	}

	public void setDisarmamentDetails(String disarmamentDetails) {
		this.disarmamentDetails = disarmamentDetails;
	}

	public ProductReceipt getProduct() {
		return product;
	}

	public void setProduct(ProductReceipt product) {
		this.product = product;
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
