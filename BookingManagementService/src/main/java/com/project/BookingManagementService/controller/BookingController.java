package com.project.BookingManagementService.controller;

import com.project.BookingManagementService.model.Booking;
import com.project.BookingManagementService.model.Train;
import com.project.BookingManagementService.service.BookingService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookingController {

    Logger logger = LoggerFactory.getLogger(BookingController.class);
    @Autowired
    BookingService bookingService;



    @PostMapping("/addbooking")
    public Booking addbooking(@RequestBody Booking booking) {
        logger.info("[addtrain] info message added");
        return bookingService.addbooking(booking);

    }

    @GetMapping("/listallbookings")
    public List<Booking> listallbookings(){
        logger.info("[listalltrains] info message added");
        return bookingService.listallbookings();
    }

    @GetMapping("/listallbookings/{email}")
    public  List<Booking> findByEmail(@PathVariable("email")String email)
    {
        return bookingService.findByEmail(email);
    }


    @GetMapping("/view/{bookingId}")
    @ApiOperation(value = "View Booking details", notes = "View all booking for particular train number")
    public Optional<Booking> viewbooking(@PathVariable("bookingId") int bookingId) {
        logger.info("[view/bookingId] info message added");

        return bookingService.viewbooking(bookingId);
    }

    @GetMapping("/viewbooking/{trainNum}")
    @ApiOperation(value = "View Booking details", notes = "View all booking for particular train number")
    public List<Booking> viewbookingbyTid(@PathVariable("trainNum") int trainNum) {
        logger.info("[viewbooking/trainNum] info message added");

        return bookingService.viewbookingbyTid(trainNum);
    }


}