package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Customers;

@Repository
public interface CustomersRepository extends CrudRepository<Customers, Long> {

	List <Customers>findAll();
	//Esta consulta es para buscar a los clientes por su nombre o apellido (coincidencias)
	@Query("SELECT c FROM Customers c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(c.lastname) LIKE LOWER(CONCAT('%', :name, '%')) OR LIKE CONCAT('%', :name, '%') OR c.alternativePhoneNumber LIKE CONCAT('%', :name, '%')")
	List<Customers> findByNameContaining(@Param("name") String name);
	// Esta consulta devuelve a los clientes por su numero de telefono (coincidencias)
	@Query("SELECT c FROM Customers c WHERE c.phoneNumber LIKE CONCAT('%', :phone, '%') OR c.alternativePhoneNumber LIKE CONCAT('%', :phone, '%')")
	List<Customers> findByPhoneContaining(@Param("phone") String phone);
	
}
