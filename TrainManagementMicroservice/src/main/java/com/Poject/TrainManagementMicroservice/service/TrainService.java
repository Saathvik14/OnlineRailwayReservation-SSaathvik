package com.Poject.TrainManagementMicroservice.service;

import com.Poject.TrainManagementMicroservice.model.Train;
import org.bson.json.JsonParseException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public interface TrainService {

    public  Train addtrain(Train train);
    public List<Train> getTrains();
    public Optional<Train> listTrain(int trainId);

    public void deleteTrain(int trainId);
    public Train updateTrain(int trainId,Train train);

     public List<Train> findBySrcAndDestination(String source, String destination);

    public List<Train> findByDest(String destination);
}
