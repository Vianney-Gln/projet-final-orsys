package com.orsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orsys.business.Utilisateur;

public interface IUtilisateurDao extends JpaRepository<Utilisateur, Long> {

	Utilisateur findUtilisateurByEmail(String email);

}
