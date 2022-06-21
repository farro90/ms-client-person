package com.nttdata.bc19.msclientperson.service.impl;

import com.nttdata.bc19.msclientperson.exception.ModelNotFoundException;
import com.nttdata.bc19.msclientperson.model.PersonClient;
import com.nttdata.bc19.msclientperson.repository.IPersonClientRepository;
import com.nttdata.bc19.msclientperson.service.IPersonClientService;
import com.nttdata.bc19.msclientperson.util.LogMessage;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.function.Consumer;

@Service
public class PersonClientServiceImpl implements IPersonClientService {

    private final Logger LOGGER = LoggerFactory.getLogger("PersonClientLog");
    private final String SAVESUCCESS = "SAVESUCCESS";
    private final String UPDATESUCCESS = "UPDATESUCCESS";
    private final String DELETESUCCESS = "DELETESUCCESS";
    @Autowired
    IPersonClientRepository iPersonClientRepository;

    @Override
    public Mono<PersonClient> create(PersonClient personClient) {
        personClient.setId(new ObjectId().toString());
        personClient.setCreatedAt(LocalDateTime.now());
        return iPersonClientRepository.save(personClient).doOnSuccess(this.doOnSucess(SAVESUCCESS));
    }

    @Override
    public Mono<PersonClient> update(PersonClient personClient) {
        personClient.setUpdatedAt(LocalDateTime.now());
        return iPersonClientRepository
                    .findById(personClient.getId())
                    .switchIfEmpty(Mono.error(new ModelNotFoundException(LogMessage.idNotFound)))
                    .flatMap(personClientFind -> {
                        return iPersonClientRepository.save(personClient).doOnSuccess(this.doOnSucess(UPDATESUCCESS));
                    });
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return iPersonClientRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new ModelNotFoundException(LogMessage.idNotFound)))
                .flatMap(personClient -> {
                    return iPersonClientRepository.deleteById(id).doOnSuccess(this.doOnSucessDelete(DELETESUCCESS));
                });
    }

    @Override
    public Mono<PersonClient> findById(String id) { return iPersonClientRepository.findById(id).switchIfEmpty(Mono.error(new ModelNotFoundException(LogMessage.idNotFound))); }

    @Override
    public Flux<PersonClient> findAll() {
        return iPersonClientRepository.findAll();
    }

    private Consumer<PersonClient> doOnSucess(String idLogMessage){
        return new Consumer<PersonClient>() {
            @Override
            public void accept(PersonClient personClient) {
                LOGGER.info(LogMessage.logMessage.get(idLogMessage));
            }
        };
    }

    private Consumer<Void> doOnSucessDelete(String idLogMessage){
        return new Consumer<Void>() {
            @Override
            public void accept(Void unused) {
                LOGGER.info(LogMessage.logMessage.get(idLogMessage));
            }
        };
    }
}
