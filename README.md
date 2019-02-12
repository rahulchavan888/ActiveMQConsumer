# ActiveMQConsumer

Spring boot web app to consume Log messages from ActiveMQ and Store into respective Databases.

Used MongoDb to store the log json.

Used Cassandra to store parsed json into respective relational tables.

# dependencies
```
<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
<dependency>
	  <groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-cassandra</artifactId>
</dependency>
```
