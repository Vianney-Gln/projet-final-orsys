package com.orsys.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orsys.business.File;
import com.orsys.dao.IFileDao;
import com.orsys.services.IFileService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileServiceImpl implements IFileService {

	private IFileDao fileDao;

	@Override
	public List<File> getFiles() {

		return fileDao.findAll();
	}

}
