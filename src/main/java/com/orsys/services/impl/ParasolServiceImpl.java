package com.orsys.services.impl;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.orsys.business.File;
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

	@Override
	public Parasol getReservationParasolByFile(File file) {
		Parasol parasol = new Parasol();
		parasol.setNumeroEmplacement((byte) -1);
		parasol.setFile(file);
		Example<Parasol> employeeExample = Example.of(parasol);
		return parasolDao.findOne(employeeExample).orElse(null);
	}

}
