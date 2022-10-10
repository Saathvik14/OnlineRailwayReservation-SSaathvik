package com.project.UserManagementService.service;

import com.project.UserManagementService.model.Booking;
import com.project.UserManagementService.model.Train;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity add(Booking booking, int trainId, int userId, String source, String destination, String Tdate)
            throws HttpClientErrorException {

        Booking response= restTemplate.postForObject("/book/add/"+trainId +"/"
                        +userId+ "/" +source+ "/" +destination+ "/" +Tdate,booking, Booking.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity viewbooking(int bookingId) throws  HttpClientErrorException {
        Booking booking=restTemplate.getForObject("/book/view/" +bookingId,Booking.class);
        return new ResponseEntity(booking,HttpStatus.OK);
    }


}
