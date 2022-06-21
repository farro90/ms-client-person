package com.nttdata.bc19.msclientperson.repository;

import com.nttdata.bc19.msclientperson.model.PersonClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonClientRepository extends ReactiveMongoRepository<PersonClient, String> {
}
