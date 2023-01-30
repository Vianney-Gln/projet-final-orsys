package com.orsys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orsys.business.File;
import com.orsys.services.impl.FileServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {

	private FileServiceImpl fileService;

	@GetMapping("files")
	List<File> getFiles() {

		return fileService.getFiles();
	}

}
