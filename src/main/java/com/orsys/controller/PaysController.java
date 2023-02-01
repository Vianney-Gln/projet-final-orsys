package com.orsys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orsys.business.Pays;
import com.orsys.services.impl.PaysServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class PaysController {

	private PaysServiceImpl paysService;

	@GetMapping("pays")
	@Operation(description = "Renvois la liste des différents pays disponible")
	List<Pays> getAllPays() {

		return paysService.getPays();
	}

	@GetMapping("pays/{id}")
	@Operation(description = "Renvois un pays par son id")
	Pays getPaysById(@PathVariable String id) {

		return paysService.getPaysById(id);
	}

}
