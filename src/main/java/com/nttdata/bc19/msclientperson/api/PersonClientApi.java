package com.nttdata.bc19.msclientperson.api;

import com.nttdata.bc19.msclientperson.model.PersonClient;
import com.nttdata.bc19.msclientperson.service.IPersonClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/client/person")
public class PersonClientApi {
    @Autowired
    private IPersonClientService personClientService;

    @PostMapping
    public Mono<PersonClient> create(@RequestBody PersonClient personClient){ return personClientService.create(personClient); }

    @PutMapping
    public Mono<PersonClient> update(@RequestBody PersonClient personClient){ return personClientService.update(personClient); }

    @GetMapping
    public Flux<PersonClient> findAll(){
        return personClientService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<PersonClient> findById(@PathVariable String id){ return personClientService.findById(id); }

    /*
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return personClientService.deleteById(id);
    }
     */
}
