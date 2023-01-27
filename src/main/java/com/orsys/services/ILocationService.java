package com.orsys.services;

import java.util.List;

import com.orsys.business.Location;
import com.orsys.dto.LocationDto;

public interface ILocationService {

	LocationDto addLocation(LocationDto location, Long... idParasols);

	List<Location> getLocationByUser(Long idUser);
}
