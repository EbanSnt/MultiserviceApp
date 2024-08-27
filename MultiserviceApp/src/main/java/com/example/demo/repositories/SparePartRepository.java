package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.SparePart;

@Repository
public interface SparePartRepository extends CrudRepository<SparePart, Long>{

	List<SparePart>findAll();
	List<SparePart>findAllBySparePartType(String sparePartType);
	@Query("SELECT s FROM SparePart s WHERE LOWER(s.sparePartName) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<SparePart>findByNameContaining(@Param("name") String name);
}
