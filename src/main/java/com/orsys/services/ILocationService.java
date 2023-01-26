package com.orsys.services;

import com.orsys.dto.LocationDto;

public interface ILocationService {

	LocationDto addLocation(LocationDto location, Long... idParasols);
}
