package com.example.examen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class PersonController {

    // Usar este comando en la terminal para iniciar aplicación:
    // java -jar build/libs/examen-0.0.1-SNAPSHOT.jar

    @Autowired
    private PersonRepository personRepository;

    // get 1 item
    @GetMapping(path = "/people/{id}")
    public @ResponseBody Optional<Person> getPerson(@PathVariable Integer id){
        return personRepository.findById(id);
    }

    // get all items
    @GetMapping(path = "/people")
    public @ResponseBody Iterable<Person> getPeople(){
        return personRepository.findAll();
    }

    // post (create)
    @PostMapping(path = "/addPerson")
    public ResponseEntity<String> addPerson(@RequestBody Person person){
        personRepository.save(person);
        return new ResponseEntity<>("Person added", HttpStatus.CREATED);
    }

    // put (update)
    @PutMapping(path = "/updatePerson/{id}")
    public @ResponseBody String updatePerson(@PathVariable Integer id, @RequestBody Person updatedPerson){
        Optional<Person> optionalPerson = personRepository.findById(id);

        if(optionalPerson.isPresent()){

            Person person = optionalPerson.get();

            if(updatedPerson.getName() != null){
                person.setName(updatedPerson.getName());
            }

            if(updatedPerson.getHeight() != null){
                person.setHeight(updatedPerson.getHeight());
            }

            if(updatedPerson.getMass() != null){
                person.setMass(updatedPerson.getMass());
            }

            if(updatedPerson.getHairColor() != null){
                person.setHairColor(updatedPerson.getHairColor());
            }

            if(updatedPerson.getSkinColor() != null){
                person.setSkinColor(updatedPerson.getSkinColor());
            }

            if(updatedPerson.getEyeColor() != null){
                person.setEyeColor(updatedPerson.getEyeColor());
            }

            if(updatedPerson.getBirthYear() != null){
                person.setBirthYear(updatedPerson.getBirthYear());
            }

            if(updatedPerson.getGender() != null){
                person.setGender(updatedPerson.getGender());
            }

            if(updatedPerson.getHomeworld() != null){
                person.setHomeworld(updatedPerson.getHomeworld());
            }

            if(updatedPerson.getCreated() != null){
                person.setCreated(updatedPerson.getCreated());
            }

            if(updatedPerson.getEdited() != null){
                person.setEdited(updatedPerson.getEdited());
            }

            if(updatedPerson.getUrl() != null){
                person.setUrl(updatedPerson.getUrl());
            }

            personRepository.save(person);
            return "Person updated";

        }

        return "Person not found";
    }

    // delete
    @DeleteMapping(path = "deletePerson/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Integer id){
        Optional<Person> optionalPerson = personRepository.findById(id);

        if(optionalPerson.isPresent()){

            try{

                personRepository.deleteById(id);
                return new ResponseEntity<>("Person deleted", HttpStatus.OK);

            }catch (Exception e){
                return new ResponseEntity<>("Couldn't delete person: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        return new ResponseEntity<>("Person not found", HttpStatus.NOT_FOUND);
    }

}
