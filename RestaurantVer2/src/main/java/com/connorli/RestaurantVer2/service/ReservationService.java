package com.connorli.RestaurantVer2.service;

import com.connorli.RestaurantVer2.repo.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ReservationService {

    private ReservationRepository reservationRepository;
    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
}
