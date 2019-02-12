package com.activemqconsumer.service;

import java.io.IOException;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.activemqconsumer.dao.CassandraDAO;
import com.activemqconsumer.model.TransactionRequest;
import com.activemqconsumer.model.TransactionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ActiveMQConsumer {

	// used MongoTemplate for storing log JSONin mogodb
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private CassandraDAO cassandraDAO;

	// method to consume log form ActiveMQ
	@JmsListener(destination = "test.queue")
	public void consumeForActiveMQ(String message) {
		System.out.println("Consumed Message From Active MQ: " + message);
		sendLogToDatabase(message);
	}

	// method for store log to mongodb and cassandra database
	public void sendLogToDatabase(String str) {

		String filterString = str.substring(str.indexOf("{"));
		try {
			JSONObject obj = new JSONObject(filterString);
			String classTypeName = obj.getString("type");
			String value = obj.getString("value");
			ObjectMapper mapp = new ObjectMapper();

			// case for parse JSON into particular class
			switch (classTypeName) {
			case "TransactionRequest":
				// storing log json mongodb database
				Document document = Document.parse(value);
				mongoTemplate.insert(document, "TransactionRequest");

				// storing log json cassandra database
				TransactionRequest transactionRequest = mapp.readValue(value, TransactionRequest.class);
				cassandraDAO.saveTransactionRequest(transactionRequest);
				break;
			case "TransactionResponse":
				// storing log json mongodb database
				Document doc = Document.parse(value);
				mongoTemplate.insert(doc, "TransactionResponse");

				// storing log json cassandra database
				TransactionResponse transactionResponse = mapp.readValue(value, TransactionResponse.class);
				cassandraDAO.saveTransactionResponse(transactionResponse);
				break;
			default:
				System.out.println("case not matched");
			}

		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
	}

}
