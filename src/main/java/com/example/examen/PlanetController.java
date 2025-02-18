package com.example.examen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api")
public class PlanetController {

    @Autowired
    private PlanetRepository planetRepository;

    // get 1 item
    @GetMapping(path = "/planet/{id}")
    public @ResponseBody Optional<Planet> getPlanet(@PathVariable Integer id){
        return planetRepository.findById(id);
    }

    // get all items
    @GetMapping(path = "/planet")
    public @ResponseBody Iterable<Planet> getPlanet(){
        return planetRepository.findAll();
    }

    // post (create)
    @PostMapping(path = "/addPlanet")
    public ResponseEntity<String> addPlanet(@RequestBody Planet planet){
        planetRepository.save(planet);
        return new ResponseEntity<>("Planet added", HttpStatus.CREATED);
    }

    // put (update)
    @PutMapping(path = "/updatePlanet/{id}")
    public @ResponseBody String updatePlanet(@PathVariable Integer id, @RequestBody Planet updatedPlanet){
        Optional<Planet> optionalPlanet = planetRepository.findById(id);

        if(optionalPlanet.isPresent()){

            Planet planet = optionalPlanet.get();

            if(updatedPlanet.getName() != null){
                planet.setName(updatedPlanet.getName());
            }

            if(updatedPlanet.getRotationPeriod() != null){
                planet.setRotationPeriod(updatedPlanet.getRotationPeriod());
            }

            if(updatedPlanet.getOrbitalPeriod() != null){
                planet.setOrbitalPeriod(updatedPlanet.getOrbitalPeriod());
            }

            if(updatedPlanet.getDiameter() != null){
                planet.setDiameter(updatedPlanet.getDiameter());
            }

            if(updatedPlanet.getClimate() != null){
                planet.setClimate(updatedPlanet.getClimate());
            }

            if(updatedPlanet.getGravity() != null){
                planet.setGravity(updatedPlanet.getGravity());
            }

            if(updatedPlanet.getTerrain() != null){
                planet.setTerrain(updatedPlanet.getTerrain());
            }

            if(updatedPlanet.getSurfaceWater() != null){
                planet.setSurfaceWater(updatedPlanet.getSurfaceWater());
            }

            if(updatedPlanet.getPopulation() != null){
                planet.setPopulation(updatedPlanet.getPopulation());
            }

            if(updatedPlanet.getCreated() != null){
                planet.setCreated(updatedPlanet.getCreated());
            }

            if(updatedPlanet.getEdited() != null){
                planet.setEdited(updatedPlanet.getEdited());
            }

            if(updatedPlanet.getUrl() != null){
                planet.setUrl(updatedPlanet.getUrl());
            }

            planetRepository.save(planet);
            return "Planet updated";

        }

        return "Planet not found";
    }

    // delete
    @DeleteMapping(path = "deletePlanet/{id}")
    public ResponseEntity<String> deletePlanet(@PathVariable Integer id){
        Optional<Planet> optionalPlanet = planetRepository.findById(id);

        if(optionalPlanet.isPresent()){

            try{

                planetRepository.deleteById(id);
                return new ResponseEntity<>("Planet deleted", HttpStatus.OK);

            }catch (Exception e){
                return new ResponseEntity<>("Couldn't delete planet: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        return new ResponseEntity<>("Planet not found", HttpStatus.NOT_FOUND);
    }

}
