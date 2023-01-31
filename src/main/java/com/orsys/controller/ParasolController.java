package com.orsys.controller;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orsys.business.Parasol;
import com.orsys.services.impl.ParasolServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class ParasolController {

	private ParasolServiceImpl parasolService;

	@GetMapping("parasols/{fileIds}")
	Map<Long, List<Parasol>> getParasolsByFile(@PathVariable @NotNull List<Long> fileIds) {

		return parasolService.getParasolsByFile(fileIds);
	}

}
