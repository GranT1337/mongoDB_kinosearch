package com.ostanin.repository;


import com.ostanin.dto.Producer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProducerRepository extends MongoRepository<Producer, String> {

}
