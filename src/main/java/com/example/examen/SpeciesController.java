package com.example.examen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api")
public class SpeciesController {

    @Autowired
    private SpeciesRepository speciesRepository;

    // get 1 item
    @GetMapping(path = "/species/{id}")
    public @ResponseBody Optional<Species> getSpecies(@PathVariable Integer id){
        return speciesRepository.findById(id);
    }

    // get all items
    @GetMapping(path = "/species")
    public @ResponseBody Iterable<Species> getSpecies(){
        return speciesRepository.findAll();
    }

    // post (create)
    @PostMapping(path = "/addSpecies")
    public ResponseEntity<String> addSpecies(@RequestBody Species species){
        speciesRepository.save(species);
        return new ResponseEntity<>("Species added", HttpStatus.CREATED);
    }

    // put (update)
    @PutMapping(path = "/updateSpecies/{id}")
    public @ResponseBody String updateSpecies(@PathVariable Integer id, @RequestBody Species updatedSpecies){
        Optional<Species> optionalSpecies = speciesRepository.findById(id);

        if(optionalSpecies.isPresent()){

            Species species = optionalSpecies.get();

            if(updatedSpecies.getName() != null){
                species.setName(updatedSpecies.getName());
            }

            if(updatedSpecies.getClassification() != null){
                species.setClassification(updatedSpecies.getClassification());
            }

            if(updatedSpecies.getDesignation() != null){
                species.setDesignation(updatedSpecies.getDesignation());
            }

            if(updatedSpecies.getAverageHeight() != null){
                species.setAverageHeight(updatedSpecies.getAverageHeight());
            }

            if(updatedSpecies.getSkinColors() != null){
                species.setSkinColors(updatedSpecies.getSkinColors());
            }

            if(updatedSpecies.getHairColors() != null){
                species.setHairColors(updatedSpecies.getHairColors());
            }

            if(updatedSpecies.getEyeColors() != null){
                species.setEyeColors(updatedSpecies.getEyeColors());
            }

            if(updatedSpecies.getAverageLifespan() != null){
                species.setAverageLifespan(updatedSpecies.getAverageLifespan());
            }

            if(updatedSpecies.getHomeworld() != null){
                species.setHomeworld(updatedSpecies.getHomeworld());
            }

            if(updatedSpecies.getLanguage() != null){
                species.setLanguage(updatedSpecies.getLanguage());
            }

            if(updatedSpecies.getCreated() != null){
                species.setCreated(updatedSpecies.getCreated());
            }

            if(updatedSpecies.getEdited() != null){
                species.setEdited(updatedSpecies.getEdited());
            }

            if(updatedSpecies.getUrl() != null){
                species.setUrl(updatedSpecies.getUrl());
            }

            speciesRepository.save(species);
            return "Species updated";

        }

        return "Species not found";
    }

    // delete
    @DeleteMapping(path = "deleteSpecies/{id}")
    public ResponseEntity<String> deleteSpecies(@PathVariable Integer id){
        Optional<Species> optionalSpecies = speciesRepository.findById(id);

        if(optionalSpecies.isPresent()){

            try{

                speciesRepository.deleteById(id);
                return new ResponseEntity<>("Species deleted", HttpStatus.OK);

            }catch (Exception e){
                return new ResponseEntity<>("Couldn't delete species: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        return new ResponseEntity<>("Species not found", HttpStatus.NOT_FOUND);
    }

}
