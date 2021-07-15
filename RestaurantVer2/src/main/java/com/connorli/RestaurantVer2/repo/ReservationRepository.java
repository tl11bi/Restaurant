package com.connorli.RestaurantVer2.repo;

import com.connorli.RestaurantVer2.domain.Employee;
import com.connorli.RestaurantVer2.domain.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Optional;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    Optional<Reservation> findByFirstNameAndLastNameAndTime(String firstName, String lastName, Date time);
}
