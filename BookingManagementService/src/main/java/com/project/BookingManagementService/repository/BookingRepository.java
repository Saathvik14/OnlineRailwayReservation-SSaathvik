package com.project.BookingManagementService.repository;

import com.project.BookingManagementService.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableMongoRepositories
public interface BookingRepository extends MongoRepository<Booking,Integer> {

        @Query("{trainNum:?0}")
        List<Booking> search( int trainNum);

        @Query("{email:?0}")
        List<Booking> findByEmail(String email);

        }

