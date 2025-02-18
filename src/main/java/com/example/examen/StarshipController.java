package com.example.examen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api")
public class StarshipController {

    @Autowired
    private StarshipRepository starshipRepository;

    // get 1 item
    @GetMapping(path = "/starship/{id}")
    public @ResponseBody Optional<Starship> getStarship(@PathVariable Integer id){
        return starshipRepository.findById(id);
    }

    // get all items
    @GetMapping(path = "/starship")
    public @ResponseBody Iterable<Starship> getStarship(){
        return starshipRepository.findAll();
    }

    // post (create)
    @PostMapping(path = "/addStarship")
    public ResponseEntity<String> addStarship(@RequestBody Starship starship){
        starshipRepository.save(starship);
        return new ResponseEntity<>("Starship added", HttpStatus.CREATED);
    }

    // put (update)
    @PutMapping(path = "/updateStarship/{id}")
    public @ResponseBody String updateStarship(@PathVariable Integer id, @RequestBody Starship updatedStarship){
        Optional<Starship> optionalStarship = starshipRepository.findById(id);

        if(optionalStarship.isPresent()){

            Starship starship = optionalStarship.get();

            if(updatedStarship.getName() != null){
                starship.setName(updatedStarship.getName());
            }

            if(updatedStarship.getModel() != null){
                starship.setModel(updatedStarship.getModel());
            }

            if(updatedStarship.getManufacturer() != null){
                starship.setManufacturer(updatedStarship.getManufacturer());
            }

            if(updatedStarship.getCostInCredits() != null){
                starship.setCostInCredits(updatedStarship.getCostInCredits());
            }

            if(updatedStarship.getLength() != null){
                starship.setLength(updatedStarship.getLength());
            }

            if(updatedStarship.getMaxAtmospheringSpeed() != null){
                starship.setMaxAtmospheringSpeed(updatedStarship.getMaxAtmospheringSpeed());
            }

            if(updatedStarship.getCrew() != null){
                starship.setCrew(updatedStarship.getCrew());
            }

            if(updatedStarship.getPassengers() != null){
                starship.setPassengers(updatedStarship.getPassengers());
            }

            if(updatedStarship.getCargoCapacity() != null){
                starship.setCargoCapacity(updatedStarship.getCargoCapacity());
            }

            if(updatedStarship.getConsumables() != null){
                starship.setConsumables(updatedStarship.getConsumables());
            }

            if(updatedStarship.getHyperdriveRating() != null){
                starship.setHyperdriveRating(updatedStarship.getHyperdriveRating());
            }

            if(updatedStarship.getMglt() != null){
                starship.setMglt(updatedStarship.getMglt());
            }

            if(updatedStarship.getStarshipClass() != null){
                starship.setStarshipClass(updatedStarship.getStarshipClass());
            }

            if(updatedStarship.getCreated() != null){
                starship.setCreated(updatedStarship.getCreated());
            }

            if(updatedStarship.getEdited() != null){
                starship.setEdited(updatedStarship.getEdited());
            }

            if(updatedStarship.getUrl() != null){
                starship.setUrl(updatedStarship.getUrl());
            }

            starshipRepository.save(starship);
            return "Starship updated";

        }

        return "Starship not found";
    }

    // delete
    @DeleteMapping(path = "deleteStarship/{id}")
    public ResponseEntity<String> deleteStarship(@PathVariable Integer id){
        Optional<Starship> optionalStarship = starshipRepository.findById(id);

        if(optionalStarship.isPresent()){

            try{

                starshipRepository.deleteById(id);
                return new ResponseEntity<>("Starship deleted", HttpStatus.OK);

            }catch (Exception e){
                return new ResponseEntity<>("Couldn't delete starship: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        return new ResponseEntity<>("Starship not found", HttpStatus.NOT_FOUND);
    }

}
