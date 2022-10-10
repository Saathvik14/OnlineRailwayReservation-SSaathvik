package com.Poject.TrainManagementMicroservice.service;

import com.Poject.TrainManagementMicroservice.customexception.InvalidTrainDateException;
import com.Poject.TrainManagementMicroservice.model.Train;
import com.Poject.TrainManagementMicroservice.repository.TrainRepository;
import org.bson.json.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImpl implements TrainService{

    @Autowired
    private TrainRepository trainRepository;


    @Override
    public Train addtrain(Train train) {
        return trainRepository.save(train);
    }
    @Override
    public List<Train> getTrains()
    {
        return trainRepository.findAll();
    }

    @Override
    public Optional<Train> listTrain(int trainId) {
        return trainRepository.findById(trainId);
    }



    @Override
    public  List<Train> findBySrcAndDestination(String source,String destination)  {

      return trainRepository.searchSrcandDest(source,destination);


    }
    @Override
    public  List<Train> findByDest(String destination) {
        return trainRepository.findByDest(destination);
    }




    @Override
    public void deleteTrain(int trainId)
    {
        trainRepository.deleteById(trainId);
    }


    @Override
    public Train updateTrain(int id, Train train) {
        Optional<Train> findById = trainRepository.findById(id);
        if(findById.isPresent()){
            Train trainUpdate=findById.get();
            if(train.getTrainName()!=null && !train.getTrainName().isEmpty())
                trainUpdate.setTrainName(train.getTrainName());
            if(train.getSource()!=null && !train.getSource().isEmpty())
                trainUpdate.setSource(train.getSource());
            if(train.getDestination()!=null && !train.getDestination().isEmpty())
                trainUpdate.setDestination(train.getDestination());
            if(train.getPrice()!=0)
                trainUpdate.setPrice(train.getPrice());
            if(train.getTimeOfArrival()!=null && !train.getTimeOfArrival().isEmpty())
                trainUpdate.setTimeOfArrival(train.getTimeOfArrival());
            if(train.getTimeOfDeparture()!=null && !train.getTimeOfDeparture().isEmpty())
                trainUpdate.setTimeOfDeparture(train.getTimeOfDeparture());
            if(train.getTotalNumOfSeats()!=0)
                trainUpdate.setTotalNumOfSeats(train.getTotalNumOfSeats());
            if(train.getClassName()!=null)
                trainUpdate.setClassName(train.getClassName());
            return trainRepository.save(trainUpdate);
        }
        return null;
    }



}

