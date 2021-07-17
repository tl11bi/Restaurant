package com.connorli.RestaurantVer2.service;

import com.connorli.RestaurantVer2.domain.Employee;
import com.connorli.RestaurantVer2.domain.Order;
import com.connorli.RestaurantVer2.domain.Reservation;
import com.connorli.RestaurantVer2.domain.RestTable;
import com.connorli.RestaurantVer2.repo.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    public Reservation createReservation(String firstName, String lastName, Date time, int numberOfPeople, RestTable restTable){
        return reservationRepository.findByFirstNameAndLastNameAndTime(firstName, lastName, time)
                .orElse(reservationRepository.save(new Reservation(firstName, lastName, time, numberOfPeople, restTable)));
    }

    public Iterable<Reservation> lookup(){return reservationRepository.findAll();}

    public long total() {
        return reservationRepository.count();
    }
}
