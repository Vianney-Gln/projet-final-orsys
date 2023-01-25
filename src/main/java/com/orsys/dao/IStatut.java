package com.orsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orsys.business.Statut;

public interface IStatut extends JpaRepository<Statut, Long> {

}
