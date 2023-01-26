package com.orsys.services.impl;

import org.springframework.stereotype.Service;

import com.orsys.business.Utilisateur;
import com.orsys.dao.IUtilisateurDao;
import com.orsys.services.IUtilisateurService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements IUtilisateurService {

	private IUtilisateurDao utilisateurDao;

	@Override
	public Utilisateur getCurrentUser(String email) {

		return utilisateurDao.findUtilisateurByEmail(email);
	}

}
