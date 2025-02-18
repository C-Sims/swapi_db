package com.example.examen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    // get 1 item
    @GetMapping(path = "/vehicle/{id}")
    public @ResponseBody Optional<Vehicle> getVehicle(@PathVariable Integer id){
        return vehicleRepository.findById(id);
    }

    // get all items
    @GetMapping(path = "/vehicle")
    public @ResponseBody Iterable<Vehicle> getVehicle(){
        return vehicleRepository.findAll();
    }

    // post (create)
    @PostMapping(path = "/addVehicle")
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle){
        vehicleRepository.save(vehicle);
        return new ResponseEntity<>("Vehicle added", HttpStatus.CREATED);
    }

    // put (update)
    @PutMapping(path = "/updateVehicle/{id}")
    public @ResponseBody String updateVehicle(@PathVariable Integer id, @RequestBody Vehicle updatedVehicle){
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

        if(optionalVehicle.isPresent()){

            Vehicle vehicle = optionalVehicle.get();

            if(updatedVehicle.getName() != null){
                vehicle.setName(updatedVehicle.getName());
            }

            if(updatedVehicle.getModel() != null){
                vehicle.setModel(updatedVehicle.getModel());
            }

            if(updatedVehicle.getManufacturer() != null){
                vehicle.setManufacturer(updatedVehicle.getManufacturer());
            }

            if(updatedVehicle.getCostInCredits() != null){
                vehicle.setCostInCredits(updatedVehicle.getCostInCredits());
            }

            if(updatedVehicle.getLength() != null){
                vehicle.setLength(updatedVehicle.getLength());
            }

            if(updatedVehicle.getMaxAtmospheringSpeed() != null){
                vehicle.setMaxAtmospheringSpeed(updatedVehicle.getMaxAtmospheringSpeed());
            }

            if(updatedVehicle.getCrew() != null){
                vehicle.setCrew(updatedVehicle.getCrew());
            }

            if(updatedVehicle.getPassengers() != null){
                vehicle.setPassengers(updatedVehicle.getPassengers());
            }

            if(updatedVehicle.getCargoCapacity() != null){
                vehicle.setCargoCapacity(updatedVehicle.getCargoCapacity());
            }

            if(updatedVehicle.getConsumables() != null){
                vehicle.setConsumables(updatedVehicle.getConsumables());
            }

            if(updatedVehicle.getVehicleClass() != null){
                vehicle.setVehicleClass(updatedVehicle.getVehicleClass());
            }

            if(updatedVehicle.getCreated() != null){
                vehicle.setCreated(updatedVehicle.getCreated());
            }

            if(updatedVehicle.getEdited() != null){
                vehicle.setEdited(updatedVehicle.getEdited());
            }

            if(updatedVehicle.getUrl() != null){
                vehicle.setUrl(updatedVehicle.getUrl());
            }

            vehicleRepository.save(vehicle);
            return "Vehicle updated";

        }

        return "Vehicle not found";
    }

    // delete
    @DeleteMapping(path = "deleteVehicle/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Integer id){
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);

        if(optionalVehicle.isPresent()){

            try{

                vehicleRepository.deleteById(id);
                return new ResponseEntity<>("Vehicle deleted", HttpStatus.OK);

            }catch (Exception e){
                return new ResponseEntity<>("Couldn't delete vehicle: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        return new ResponseEntity<>("Vehicle not found", HttpStatus.NOT_FOUND);
    }

}
