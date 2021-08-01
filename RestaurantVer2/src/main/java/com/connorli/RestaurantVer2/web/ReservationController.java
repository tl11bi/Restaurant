package com.connorli.RestaurantVer2.web;

import com.connorli.RestaurantVer2.domain.Reservation;
import com.connorli.RestaurantVer2.repo.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/reservations")
public class ReservationController {
    private final ReservationRepository reservationRepository;

    @Autowired
    ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }



    @GetMapping
    List<Reservation> all() {
        return reservationRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Reservation newReservation(@RequestBody Reservation newReservation) {
        return reservationRepository.save(newReservation);
    }

    // Single item

    @GetMapping("/{reservationID}")
    Reservation one(@PathVariable Long reservationID) {

        return reservationRepository.findById(reservationID)
                .orElseThrow(() -> new NoSuchElementException());
    }

    @PutMapping("/{reservationID}")
    Reservation replaceReservation(@RequestBody Reservation newReservation, @PathVariable(value = "reservationID") Long reservationID) {

        return reservationRepository.findById(reservationID)
                .map(reservation -> {
                    reservation.setFirstName(newReservation.getFirstName());
                    reservation.setLastName(newReservation.getLastName());
                    reservation.setNumberOfPeople(newReservation.getNumberOfPeople());
                    reservation.setRestTable(newReservation.getRestTable());
                    reservation.setTime(newReservation.getTime());
                    return reservationRepository.save(reservation);
                })
                .orElseGet(() -> {
                    newReservation.setReservationID(reservationID);
                    return reservationRepository.save(newReservation);
                });
    }

    @DeleteMapping("/{reservationID}")
    void deleteReservation(@PathVariable Long reservationID) {
        reservationRepository.deleteById(reservationID);
    }

}

