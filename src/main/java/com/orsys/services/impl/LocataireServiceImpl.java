package com.orsys.services.impl;

import org.springframework.stereotype.Service;

import com.orsys.business.Locataire;
import com.orsys.dao.ILocataireDao;
import com.orsys.services.ILocataireService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocataireServiceImpl implements ILocataireService {

	private ILocataireDao locataireDao;

	@Override
	public Locataire addLocataire(Locataire locataire) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locataire getLocataire(Long id) {
		return locataireDao.findById(id).orElse(null);
	}

}
