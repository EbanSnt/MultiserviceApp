package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ProductReceipt;

@Repository
public interface ProductReceiptRepository extends CrudRepository<ProductReceipt, Long>{

	List<ProductReceipt>findAll();
	@Query("SELECT p FROM ProductReceipt p WHERE LOWER(p.numberReceipt) LIKE LOWER(CONCAT('%', :number, '%'))")
	List<ProductReceipt>findByNumberContaining(@Param("number") String number);
	List<ProductReceipt>findAllByProduct(String product);
	List<ProductReceipt>findAllByReceivedBy(String receivedBy);
	List<ProductReceipt>findAllByReceiptDate(Date receiptDate);
	List<ProductReceipt>findAllByOrderByReceiptDateAsc();
	List<ProductReceipt>findAllByOrderByReceiptDateDesc();
}
