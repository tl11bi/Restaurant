package com.connorli.RestaurantVer2.repo;

import com.connorli.RestaurantVer2.domain.Employee;
import com.connorli.RestaurantVer2.domain.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
}
