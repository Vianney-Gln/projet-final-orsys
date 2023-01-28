package com.orsys.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.orsys.business.Concessionnaire;
import com.orsys.business.File;
import com.orsys.business.Locataire;
import com.orsys.business.Location;
import com.orsys.business.Parasol;
import com.orsys.business.Statut;
import com.orsys.business.Utilisateur;
import com.orsys.dao.ILocationDao;
import com.orsys.dao.IUtilisateurDao;
import com.orsys.dto.DemandeReservationDto;
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
	private FileServiceImpl fileService;

	@Override
	public LocationDto addLocation(DemandeReservationDto demandeReservationDto) {

		Concessionnaire concessionnaire = concessionnaireService.getConcessionnaire(1L);
		Locataire locataire = locataireService.getLocataire(demandeReservationDto.getIdLocataire());
		Statut statut = statutService.getStatut(1L);
		List<File> files = fileService.getFiles();
		List<Parasol> listParasols = new ArrayList<>();

		Location location = new Location();
		demandeReservationDto.getRequestedFiles().forEach(requestedFile -> {
			Parasol parasol = new Parasol();
			parasol.setFile(files.stream().filter(item -> item.getId().equals(requestedFile.getSelectedFile())).toList()
					.get(0));
			listParasols.add(parasol);
			location.setMontantEnEuros(location.getMontantEnEuros() + parasol.getFile().getPrixJournalier());
		});

		location.setConcessionnaire(concessionnaire);
		location.setLocataire(locataire);
		location.setStatut(statut);
		location.setParasols(listParasols);

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
