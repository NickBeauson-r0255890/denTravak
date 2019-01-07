package controller;

import db.SandwichRepository;
import model.Sandwich;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

//import javax.inject.Inject;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SandwichController {

    /*@Inject
    private DiscoveryClient discoveryClient;*/


    private SandwichRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public SandwichController(SandwichRepository repository){
        this.repository = repository;
    }

    @RequestMapping
    public String homepage(){
        return "Welkom bij Den Travak!";
    }

    @RequestMapping(value = "/sandwiches", method = RequestMethod.GET)
    public Iterable<Sandwich> getSandwiches() {
        return repository.findAll();
    }

    @RequestMapping(value="/sandwiches/{id}")
    public Sandwich getSandwich(@PathVariable("id") UUID id){
        Sandwich sandwich = repository.findById(id).get();
        return sandwich;
    }

    @RequestMapping(value = "/sandwiches", method = RequestMethod.POST)
    public Sandwich addSandwich(@RequestBody Sandwich sandwich) {
        repository.save(sandwich);
        return sandwich;
    }

    @RequestMapping(value="/sandwiches/{id}",method=RequestMethod.PUT)
    public Sandwich updateSandwich(@PathVariable UUID id, @RequestBody Sandwich sandwich){
        /*Sandwich OldSandwich = repository.findById(id).get();
            OldSandwich.setName(sandwich.getName());
            OldSandwich.setPrice(sandwich.getPrice());
            OldSandwich.setIngredients(sandwich.getIngredients());
        repository.save(OldSandwich);
        return OldSandwich;*/
        return repository.save(sandwich);
    }

    @RequestMapping(value="/deleteSandwich/{id}", method=RequestMethod.DELETE)
    public void deleteSandwich(@PathVariable("id") UUID id){
        repository.deleteById(id);
    }
/*
    public Optional<URI> recommendationServiceUrl() {
        return discoveryClient.getInstances("recommendation")
                .stream()
                .map(si -> si.getUri())
                .findFirst();
    }*/
}
