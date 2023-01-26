package com.orsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orsys.business.Concessionnaire;

public interface IConcessionnaireDao extends JpaRepository<Concessionnaire, Long> {

}
