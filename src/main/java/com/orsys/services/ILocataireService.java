package com.orsys.services;

import com.orsys.business.Locataire;
import com.orsys.dto.LocataireDto;

public interface ILocataireService {

	LocataireDto addLocataire(LocataireDto locataireDto);

	Locataire getLocataire(Long id);

}
