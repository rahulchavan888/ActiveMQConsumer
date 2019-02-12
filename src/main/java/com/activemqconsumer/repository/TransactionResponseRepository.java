package com.activemqconsumer.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.activemqconsumer.model.TransactionResponse;

public interface TransactionResponseRepository extends CassandraRepository<TransactionResponse, String> {

}
