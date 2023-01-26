package com.orsys.services.impl;

import org.springframework.stereotype.Service;

import com.orsys.business.Statut;
import com.orsys.dao.IStatutDao;
import com.orsys.services.IStatutService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatutServiceImpl implements IStatutService {

	private IStatutDao statutDao;

	@Override
	public Statut addStatut(Statut statut) {

		return null;
	}

	@Override
	public Statut getStatut(Long id) {

		return statutDao.findById(id).orElse(null);
	}

}
