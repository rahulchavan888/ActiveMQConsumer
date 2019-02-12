package com.activemqconsumer.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.activemqconsumer.model.TransactionRequest;

public interface TransactionRequestRepository extends CassandraRepository<TransactionRequest, String> {

}
