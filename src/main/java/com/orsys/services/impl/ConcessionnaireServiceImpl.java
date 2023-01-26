package com.orsys.services.impl;

import org.springframework.stereotype.Service;

import com.orsys.business.Concessionnaire;
import com.orsys.dao.IConcessionnaireDao;
import com.orsys.services.IConcessionnaireService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConcessionnaireServiceImpl implements IConcessionnaireService {

	private IConcessionnaireDao concessionnaireDao;

	@Override
	public Concessionnaire addConcessionnaire(Concessionnaire concessionnaire) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Concessionnaire getConcessionnaire(Long id) {

		return concessionnaireDao.findById(id).orElse(null);
	}

}
