package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long>{

	List <Users>findAll();
	Users findByUsername(String username);
}
