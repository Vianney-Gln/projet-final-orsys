package com.orsys.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.orsys.business.Concessionnaire;
import com.orsys.business.Locataire;
import com.orsys.business.Location;
import com.orsys.business.Parasol;
import com.orsys.business.Statut;
import com.orsys.business.Utilisateur;
import com.orsys.dao.ILocationDao;
import com.orsys.dao.IUtilisateurDao;
import com.orsys.dto.LocationDto;
import com.orsys.exceptions.LocationsInexistantesException;
import com.orsys.exceptions.UtilisateurInexistantException;
import com.orsys.mapper.LocationMapper;
import com.orsys.services.ILocationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements ILocationService {

	private LocationMapper locationMapper;
	private ConcessionnaireServiceImpl concessionnaireService;
	private LocataireServiceImpl locataireService;
	private StatutServiceImpl statutService;
	private ParasolServiceImpl parasolService;
	private UtilisateurServiceImpl utilisateurService;
	private IUtilisateurDao utilisateurDao;
	private ILocationDao locationDao;

	@Override
	public LocationDto addLocation(LocationDto locationDto, Long... idParasols) {

		Concessionnaire concessionnaire = concessionnaireService.getConcessionnaire(locationDto.getIdConcessionnaire());
		Locataire locataire = locataireService.getLocataire(locationDto.getIdLocataire());
		Statut statut = statutService.getStatut(locationDto.getIdStatut());
		Location location = locationMapper.toEntity(locationDto);
		List<Parasol> listParasols = new ArrayList<>();
		double prix = 0;

		location.setConcessionnaire(concessionnaire);
		location.setLocataire(locataire);
		location.setStatut(statut);

		for (int i = 0; i < idParasols.length; i++) {
			Parasol parasol = parasolService.getParasol(idParasols[i]);
			listParasols.add(parasol);
			prix += parasol.getFile().getPrixJournalier();

		}

		location.setParasols(listParasols);
		location.setMontantEnEuros(prix);

		return locationMapper.toDto(locationDao.save(location));
	}

	@Override
	public List<Location> getLocationByUser(Long idUser) {

		if (utilisateurDao.existsById(idUser)) {
			Utilisateur utilisateur = utilisateurService.getCurrentUser(idUser);

			if (utilisateur instanceof Locataire locataire) {
				locataire = (Locataire) utilisateur;
				return locataire.getLocations();
			} else {
				throw new LocationsInexistantesException("Cet utilisateur ne possède pas de réservation");
			}
		} else {
			throw new UtilisateurInexistantException("Cet utilisateur n'existe pas en base");
		}

	}

}
