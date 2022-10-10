package com.project.BookingManagementService.service;

import com.project.BookingManagementService.model.Booking;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface BookingService {



    public List<Booking> viewbookingbyTid(int trainNum);

    public Optional<Booking> viewbooking(int bookingId);


   public List<Booking> findByEmail(String email);

    public Booking addbooking(Booking booking);

   public List<Booking> listallbookings();
}
