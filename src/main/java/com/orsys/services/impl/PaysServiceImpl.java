package com.orsys.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orsys.business.Pays;
import com.orsys.dao.IPaysDao;
import com.orsys.services.IPaysService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaysServiceImpl implements IPaysService {

	private IPaysDao paysDao;

	@Override
	public List<Pays> getPays() {

		return paysDao.findAll();
	}

	@Override
	public Pays getPaysById(String id) {

		return paysDao.findById(id).orElse(null);
	}

}
