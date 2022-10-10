package com.project.UserManagementService.controller;

import com.project.UserManagementService.model.Booking;
import com.project.UserManagementService.model.User;
import com.project.UserManagementService.service.BookingService;
import com.project.UserManagementService.service.TrainService;
import com.project.UserManagementService.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TrainService trainService;

    @Autowired
    private BookingService bookingService;


    @PostMapping("/addUser")
    @ApiOperation(value = " Register User Details ",
            notes = "Provide the User Details")
    public User add(@Valid @RequestBody User user) {
        logger.info("[addUser] info message added");

        return userService.add(user);
    }

    @GetMapping("/viewusers")
    @ApiOperation(value = "Admin can view all user Data ",
            notes = "Displays all the user Details")
    public List<User> getUsers(){
        logger.info("[viewusers] info message added");
        return userService.getUsers();
    }

    @GetMapping("/view/{userId}")
    @ApiOperation(value = "To View the specific user Details ",
            notes = "Provide the Id to view the specific user Details")
    public Optional<User> view(@PathVariable("userId") int userId) {
        logger.info("[view/userId] info message added");

        return userService.viewbyId(userId);
    }

    @DeleteMapping("/delete/{userId}")
    @ApiOperation(value = "Deletion of User Details ",
            notes = "Provide the id to delete the specific User Details")
    public String deleteUser(@PathVariable("userId") int userId) {
        logger.info("[delete/userId] info message added");
        userService.deleteUser(userId);
        return "deleted successfully";
    }

    @PutMapping("/update/{userId}")
    @ApiOperation(value = "Updation of User Details ",
            notes = "Provide the id to update the specific user Details")
    public User updateTrain(@RequestBody User user, @PathVariable("userId") int userId) {
        logger.info("[update/userId] info message added");
        return userService.updateUser(userId, user);
    }

    @GetMapping("/findby")
    @ApiOperation(value = "User can view train required to his/her choice.",
            notes = "Displays all the Trains Available")
    public ResponseEntity findByloc(@RequestParam String source,
                                    @RequestParam String destination,
                                    @RequestParam String Tdate) {

        logger.info("[findby] info message added");

            return  trainService.findBySourceDestDate(source, destination, Tdate);

    }

    @PostMapping("/add/{trainId}/{userId}/{source}/{destination}/{Tdate}")
    @ApiOperation(value = " Register Booking Details ",
            notes = "Provide the Booking Details")
    public ResponseEntity add(@RequestBody @Valid Booking booking, @PathVariable("trainId") int trainId,
                       @PathVariable("userId") int userId, @PathVariable("source") String source, @PathVariable
                               ("destination") String destination, @PathVariable("Tdate") String Tdate) {
        logger.info("[add] info message added");

        return bookingService.add(booking, trainId, userId, source, destination, Tdate);
    }

    @GetMapping("/viewbooking/{bookingId}")
    @ApiOperation(value = "User can search ticket  Detail by Id ",
            notes = "Provide the booking Id to view the required Train Details")
    public  ResponseEntity viewbooking(@PathVariable(value = "bookingId") int bookingId) {
        logger.info("[viewbooking/bookingId] info message added");

        return bookingService.viewbooking(bookingId);

    }



}
