package com.example.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.History;

@Repository
public interface HistoryRepository extends CrudRepository<History, Long>{

	List <History>findAll();
	List <History>findAllByTableName(String tableName);
	List<History> findByHistoryDate(Date historyDate);
	List <History>findAllByAction(String action);

}
