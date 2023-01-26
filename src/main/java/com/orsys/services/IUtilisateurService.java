package com.orsys.services;

import com.orsys.business.Utilisateur;

public interface IUtilisateurService {

	Utilisateur getCurrentUser(String email);

}
