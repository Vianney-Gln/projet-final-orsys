package com.orsys.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orsys.business.LienDeParente;
import com.orsys.dao.ILienDeParenteDao;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LienDeParenteServiceImpl implements ILienDeParenteService {

	private ILienDeParenteDao lienDeParenteDao;

	@Override
	public List<LienDeParente> getLienDeParente() {

		return lienDeParenteDao.findAll();
	}

	@Override
	public LienDeParente getLienDeParenteById(Long id) {

		return lienDeParenteDao.findById(id).orElse(null);
	}

}
