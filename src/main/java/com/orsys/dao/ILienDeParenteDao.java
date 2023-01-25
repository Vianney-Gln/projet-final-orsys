package com.orsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orsys.business.LienDeParente;

public interface ILienDeParenteDao extends JpaRepository<LienDeParente, Long> {

}
