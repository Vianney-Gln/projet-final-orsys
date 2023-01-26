package com.orsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orsys.business.File;

public interface IFileDao extends JpaRepository<File, Long> {

}
