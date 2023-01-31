package com.orsys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orsys.business.LienDeParente;
import com.orsys.services.LienDeParenteServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class LienDeParenteController {

	private LienDeParenteServiceImpl lienDeParenteService;

	@GetMapping("lienDeParente")
	List<LienDeParente> getLienDeParentes() {

		return lienDeParenteService.getLienDeParente();

	}

	@GetMapping("lienDeParente/{id}")
	LienDeParente getLienDeParenteById(@PathVariable Long id) {

		return lienDeParenteService.getLienDeParenteById(id);

	}

}
