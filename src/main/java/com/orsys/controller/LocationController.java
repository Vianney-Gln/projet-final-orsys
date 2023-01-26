package com.orsys.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orsys.dto.LocationDto;
import com.orsys.services.impl.LocationServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class LocationController {

	private LocationServiceImpl locationService;

	@PostMapping("location")
	LocationDto addLocation(@RequestBody LocationDto locationDto, @RequestParam Long... idParasols) {

		return locationService.addLocation(locationDto, idParasols);

	}

}
