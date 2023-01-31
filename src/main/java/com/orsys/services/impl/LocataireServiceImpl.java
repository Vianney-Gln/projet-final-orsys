package com.orsys.services.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.orsys.business.Locataire;
import com.orsys.dao.ILocataireDao;
import com.orsys.dao.IUtilisateurDao;
import com.orsys.dto.LocataireDto;
import com.orsys.exceptions.UtilisateurAlreadyExistException;
import com.orsys.mapper.LocataireMapper;
import com.orsys.services.ILocataireService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocataireServiceImpl implements ILocataireService {

	private ILocataireDao locataireDao;
	private IUtilisateurDao utilisateurDao;
	private LocataireMapper locataireMapper;

	@Override
	public Locataire getLocataire(Long id) {
		return locataireDao.findById(id).orElse(null);
	}

	@Override
	public LocataireDto addLocataire(LocataireDto locataireDto) {

		String currentUserEmail = locataireDto.getEmail();
		Locataire newLocataire = locataireMapper.toEntity(locataireDto);

		if (utilisateurDao.findUtilisateurByEmail(currentUserEmail) == null) {

			newLocataire.setDateHeureInscription(LocalDateTime.now());
			return locataireMapper.toDto(locataireDao.save(newLocataire));
		}

		throw new UtilisateurAlreadyExistException("Cet utilisateur existe déjà");
	}

}
