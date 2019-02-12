package com.activemqconsumer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.activemqconsumer.model.TransactionRequest;
import com.activemqconsumer.model.TransactionResponse;
import com.activemqconsumer.repository.TransactionRequestRepository;
import com.activemqconsumer.repository.TransactionResponseRepository;

//Class for Cassandera database related operation
@Repository
@Transactional
public class CassandraDAO {

	@Autowired
	private TransactionRequestRepository requestRepository;

	@Autowired
	TransactionResponseRepository responseRepository;

	public void saveTransactionResponse(TransactionResponse response) {
		TransactionResponse obj = responseRepository.save(response);
		System.out.println("Object saved in cassandera: " + obj);
	}

	public void saveTransactionRequest(TransactionRequest request) {
		TransactionRequest obj = requestRepository.save(request);
		System.out.println("Object saved in cassandera: " + obj);

	}

}
