package com.orsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orsys.business.Location;

public interface ILocationDao extends JpaRepository<Location, Long> {

}
