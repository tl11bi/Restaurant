package com.connorli.RestaurantVer2.service;

import com.connorli.RestaurantVer2.repo.ReservationRepository;

public class RestTableService {
    private ReservationRepository reservationRepository;

    public RestTableService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
}
