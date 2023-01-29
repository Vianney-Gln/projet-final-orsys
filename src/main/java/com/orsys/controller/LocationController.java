package com.orsys.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orsys.business.Location;
import com.orsys.dto.DemandeReservationDto;
import com.orsys.dto.LocationDto;
import com.orsys.exceptions.LocationsInexistantesException;
import com.orsys.exceptions.OutOfDateException;
import com.orsys.services.impl.LocationServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class LocationController {

	private LocationServiceImpl locationService;

	@PostMapping("location")
	LocationDto addLocation(@RequestBody DemandeReservationDto demandeReservationDto) {

		return locationService.addLocation(demandeReservationDto);
	}

	@GetMapping("location/user/{id}")
	List<Location> getLocationsByUser(@PathVariable Long id) {

		return locationService.getLocationByUser(id);
	}
	// EXCEPTIONS

	@ExceptionHandler(LocationsInexistantesException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	public String traiterLocationsInexistantes(Exception exception) {
		return exception.getMessage();
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	public String traiterChampsInexistant(Exception exception) {

		return exception.getMessage();
	}

	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	public String traiterChampsVides(Exception exception) {
		return exception.getMessage();
	}

	@ExceptionHandler(OutOfDateException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	public String traiterDatesInvalides(Exception exception) {
		return exception.getMessage();
	}
}
