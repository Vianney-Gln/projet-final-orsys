package com.orsys.services;

import java.util.List;

import com.orsys.business.Location;
import com.orsys.dto.DemandeReservationDto;
import com.orsys.dto.LocationDto;

public interface ILocationService {

	LocationDto addLocation(DemandeReservationDto demandeReservation);

	List<Location> getLocationByUser(Long idUser);

	List<Location> getAllLocations();

}
