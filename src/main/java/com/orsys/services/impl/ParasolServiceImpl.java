package com.orsys.services.impl;

import org.springframework.stereotype.Service;

import com.orsys.business.Parasol;
import com.orsys.dao.IParasolDao;
import com.orsys.services.IParasolService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ParasolServiceImpl implements IParasolService {

	private IParasolDao parasolDao;

	@Override
	public Parasol addParasol(Parasol parasol) {

		return parasolDao.save(parasol);
	}

	@Override
	public Parasol getParasol(Long id) {

		return parasolDao.findById(id).orElse(null);
	}

}
