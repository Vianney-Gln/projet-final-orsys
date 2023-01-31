package com.orsys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.orsys.business.File;

public interface IFileDao extends JpaRepository<File, Long> {

	@Query("select o from File o where id in :ids")
	List<File> findByFileIds(@Param("ids") List<Long> fileIds);
}
