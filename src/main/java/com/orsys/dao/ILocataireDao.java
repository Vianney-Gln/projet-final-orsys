package com.orsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orsys.business.Locataire;

public interface ILocataireDao extends JpaRepository<Locataire, Long> {

}
