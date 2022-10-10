package com.project.UserManagementService.service;

import com.project.UserManagementService.model.Booking;
import com.project.UserManagementService.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class TrainService {
    @Autowired
    private RestTemplate restTemplate;



    public ResponseEntity<Train[]> findBySourceDestDate(String source, String destination, String Tdate) throws HttpClientErrorException {
        try {
            ResponseEntity<Train[]> response = restTemplate.getForEntity("/train/findby/" + source + "/" + destination + "/" + Tdate, Train[].class);
            Train[] trains = response.getBody();
            return ResponseEntity.ok().body(trains);
        } catch (HttpClientErrorException httpClientErrorException) {
            return new ResponseEntity(httpClientErrorException.getResponseBodyAsString(),HttpStatus.CONFLICT );
        }
    }



}
