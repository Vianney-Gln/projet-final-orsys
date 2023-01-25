package com.orsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orsys.business.Pays;

public interface IPays extends JpaRepository<Pays, String> {

}
