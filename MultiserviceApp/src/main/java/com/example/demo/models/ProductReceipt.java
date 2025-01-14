package com.example.demo.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="product_receipt")
public class ProductReceipt {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date receiptDate;
	
	@NotEmpty
	private String numberReceipt;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="customer_id")
	private Customers customer;
	
	@NotEmpty
	private String product;
	
	@NotEmpty
	private String productBrand;
	
	@NotEmpty
	private String productModel;
	
	@NotEmpty
	private String productSerialNumber;
	
	@NotEmpty
	private String productFailure;
	
	private String productDetails;
	
	@NotEmpty
	private String receivedBy;
	
    @Transient // No guardar en la base de datos
    private MultipartFile productImage1;
    private MultipartFile productImage2;
    private MultipartFile productImage3;
    private MultipartFile productImage4;

    private String rutaProductImage1; 
    private String rutaProductImage2; 
    private String rutaProductImage3; 
    private String rutaProductImage4;
    
    @OneToMany(mappedBy="product", fetch=FetchType.LAZY)
	private List<Diagnosis> productDiagnosis;
    
    @OneToMany(mappedBy="product", fetch=FetchType.LAZY)
	private List<Communications> productCommunications;
    
    @OneToMany(mappedBy="product", fetch=FetchType.LAZY)
	private List<PickUp> productPickup;
    
    @OneToMany(mappedBy="product", fetch=FetchType.LAZY)
	private List<Warranty> productWarranty;
    
    @OneToMany(mappedBy="product", fetch=FetchType.LAZY)
	private List<Disarmaments> productDisarmaments;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
	public ProductReceipt() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getNumberReceipt() {
		return numberReceipt;
	}

	public void setNumberReceipt(String numberReceipt) {
		this.numberReceipt = numberReceipt;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getProductSerialNumber() {
		return productSerialNumber;
	}

	public void setProductSerialNumber(String productSerialNumber) {
		this.productSerialNumber = productSerialNumber;
	}

	public String getProductFailure() {
		return productFailure;
	}

	public void setProductFailure(String productFailure) {
		this.productFailure = productFailure;
	}

	public String getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public MultipartFile getProductImage1() {
		return productImage1;
	}

	public void setProductImage1(MultipartFile productImage1) {
		this.productImage1 = productImage1;
	}

	public MultipartFile getProductImage2() {
		return productImage2;
	}

	public void setProductImage2(MultipartFile productImage2) {
		this.productImage2 = productImage2;
	}

	public MultipartFile getProductImage3() {
		return productImage3;
	}

	public void setProductImage3(MultipartFile productImage3) {
		this.productImage3 = productImage3;
	}

	public MultipartFile getProductImage4() {
		return productImage4;
	}

	public void setProductImage4(MultipartFile productImage4) {
		this.productImage4 = productImage4;
	}

	public String getRutaProductImage1() {
		return rutaProductImage1;
	}

	public void setRutaProductImage1(String rutaProductImage1) {
		this.rutaProductImage1 = rutaProductImage1;
	}

	public String getRutaProductImage2() {
		return rutaProductImage2;
	}

	public void setRutaProductImage2(String rutaProductImage2) {
		this.rutaProductImage2 = rutaProductImage2;
	}

	public String getRutaProductImage3() {
		return rutaProductImage3;
	}

	public void setRutaProductImage3(String rutaProductImage3) {
		this.rutaProductImage3 = rutaProductImage3;
	}

	public String getRutaProductImage4() {
		return rutaProductImage4;
	}

	public void setRutaProductImage4(String rutaProductImage4) {
		this.rutaProductImage4 = rutaProductImage4;
	}
	
	
	public List<Diagnosis> getProductDiagnosis() {
		return productDiagnosis;
	}

	public void setProductDiagnosis(List<Diagnosis> productDiagnosis) {
		this.productDiagnosis = productDiagnosis;
	}

	public List<Communications> getProductCommunications() {
		return productCommunications;
	}

	public void setProductCommunications(List<Communications> productCommunications) {
		this.productCommunications = productCommunications;
	}

	public List<PickUp> getProductPickup() {
		return productPickup;
	}

	public void setProductPickup(List<PickUp> productPickup) {
		this.productPickup = productPickup;
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

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public List<Warranty> getProductWarranty() {
		return productWarranty;
	}

	public void setProductWarranty(List<Warranty> productWarranty) {
		this.productWarranty = productWarranty;
	} 
    
	public List<Disarmaments> getProductDisarmaments() {
		return productDisarmaments;
	}

	public void setProductDisarmaments(List<Disarmaments> productDisarmaments) {
		this.productDisarmaments = productDisarmaments;
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
