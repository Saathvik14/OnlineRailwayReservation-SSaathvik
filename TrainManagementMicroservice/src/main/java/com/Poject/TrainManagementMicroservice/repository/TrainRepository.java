package com.Poject.TrainManagementMicroservice.repository;

import com.Poject.TrainManagementMicroservice.model.Train;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TrainRepository extends MongoRepository<Train, Integer> {

    @Query("{source:?0,destination:?1,days:?2}")
    List<Train> search(String source,String destination,String daysOfRunning);

    @Query("{source:?0,destination:?1}")
    List<Train> searchSrcandDest(String source, String destination);

    @Query("{destination:?0}")
    List<Train> findByDest(String destination);
}
