package com.orsys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orsys.business.File;
import com.orsys.business.Parasol;
import com.orsys.services.impl.ParasolServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class ParasolController {

	private ParasolServiceImpl parasolService;

	@GetMapping("parasols")
	List<Parasol> getParasolsByFile(@RequestBody File file) {

		return parasolService.getParasolsByFile(file);
	}

}
