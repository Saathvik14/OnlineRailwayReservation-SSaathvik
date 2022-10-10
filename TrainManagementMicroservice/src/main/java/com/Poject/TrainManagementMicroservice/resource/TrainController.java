package com.Poject.TrainManagementMicroservice.resource;


import com.Poject.TrainManagementMicroservice.customexception.InvalidTrainDateException;
import com.Poject.TrainManagementMicroservice.model.Train;
import com.Poject.TrainManagementMicroservice.service.TrainService;
import io.swagger.annotations.ApiOperation;
import org.bson.json.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/train")
public class TrainController {

    Logger logger = LoggerFactory.getLogger(TrainController.class);

    @Autowired
    private TrainService trainService;

    @PostMapping("/addtrain")
    @ApiOperation(value = "Admin can Add Train Details ",
            notes = "Provide the Train Details")
    public Train addtrain(@RequestBody Train train) {
        logger.info("[addtrain] info message added");
        return trainService.addtrain(train);

    }

    @GetMapping("/listalltrains")
    @ApiOperation(value = "Admin can view all Train Data ",
            notes = "Displays all the Train Details")
    public List<Train> getTrains() {
        logger.info("[listalltrains] info message added");
        return trainService.getTrains();
    }

    @GetMapping("/viewtrainbyno/{trainId}")
    @ApiOperation(value = "Admin can search specific Train Detail by Id ",
            notes = "Provide the train Id to view the required Train Details")
    public Optional<Train> listTrain(@PathVariable("trainId") int trainId) {
        logger.info("[viewtrainbyno/trainId] info message added");

        return trainService.listTrain(trainId);
    }


    @DeleteMapping("/delete/{trainId}")
    @ApiOperation(value = "Admin can Delete Train Details by Id ",
            notes = "Provide the Id to delete the specific Train Details")
    public String deleteTrain(@PathVariable("trainId") int trainId) {
        logger.info("[delete/trainId] info message added");
        trainService.deleteTrain(trainId);
        return "data deleted successfully";
    }

    @PutMapping("/update/{trainId}")
    @ApiOperation(value = "Admin can update Train Details ",
            notes = "Provide the Id to update the specific Train Details")
    public Train updateTrain(@RequestBody Train train, @PathVariable("trainId") int trainId) {
        logger.info("[update/trainId] info message added");
        return trainService.updateTrain(trainId, train);
    }

    @GetMapping("/findby/{source}/{destination}")
    public List<Train> findByloc(@PathVariable("source") String source, @PathVariable("destination") String destination) {

        //logger implementation
        logger.info("[findto/source/destination] info message added");
        logger.debug("[findto/source/destination] debug message added");

        return trainService.findBySrcAndDestination(source, destination);
    }

    @GetMapping("/listalltrains/{destination}")
    public List<Train> findByDest(@PathVariable("destination") String destination) {
        return trainService.findByDest(destination);
    }

}



