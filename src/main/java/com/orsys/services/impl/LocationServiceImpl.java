package com.orsys.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.orsys.dto.TraitementLocationDto;
import com.orsys.exceptions.InexistantLocationException;
import com.orsys.exceptions.LocationsInexistantesException;
import com.orsys.exceptions.OutOfDateException;
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
	private UtilisateurServiceImpl utilisateurService;
	private IUtilisateurDao utilisateurDao;
	private ILocationDao locationDao;
	private FileServiceImpl fileService;
	private ParasolServiceImpl parasolService;

	@Override
	public LocationDto addLocation(DemandeReservationDto demandeReservationDto)
			throws NullPointerException, ArrayIndexOutOfBoundsException {

		Concessionnaire concessionnaire = concessionnaireService.getConcessionnaire(1L);
		Locataire locataire = locataireService.getLocataire(demandeReservationDto.getIdLocataire());
		Statut statut = statutService.getStatut(1L);
		List<File> files = fileService.getFiles();
		Location location = new Location();
		List<Parasol> listParasols = new ArrayList<>();

		// Gestion des files
		demandeReservationDto.getRequestedFiles().forEach(requestedFile -> {

			File file = files.stream().filter(item -> item.getId().equals(requestedFile.getSelectedFile())).toList()
					.get(0);
			Parasol parasol = parasolService.getReservationParasolByFile(file);
			listParasols.add(parasol);
			location.setMontantEnEuros(location.getMontantEnEuros() + parasol.getFile().getPrixJournalier());
		});

		// Gestion des propriétés de Location
		location.setConcessionnaire(concessionnaire);
		location.setLocataire(locataire);
		location.setStatut(statut);
		location.setParasols(listParasols);
		location.setRemarques(demandeReservationDto.getRemarques());

		// Gestion des dates
		LocalDateTime dateHeureDebut = LocalDateTime.parse(demandeReservationDto.getDateHeureDebut() + "T08:00");
		LocalDateTime dateHeureFin = LocalDateTime.parse(demandeReservationDto.getDateHeureFin() + "T18:00");
		int currentYear = LocalDate.now().getYear();
		LocalDateTime debutSaison = LocalDateTime.parse(currentYear + "-06-01T08:00");
		LocalDateTime finSaison = LocalDateTime.parse(currentYear + "-09-15T08:00");
		location.setDateHeureDebut(dateHeureDebut);
		location.setDateHeureFin(dateHeureFin);

		// Impose une période durant laquelle un client peut réserver ( entre le 01 Juin
		// et le 15 Septembre de chaque années)
		if (dateHeureDebut.isBefore(debutSaison) || dateHeureFin.isAfter(finSaison)) {
			throw new OutOfDateException(
					"Les réservations ne peuvent se faire qu'entre le 01 Juin et le 15 Septembre.");
		}

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

	@Override
	public List<Location> getAllLocations() {

		return locationDao.findAll();
	}

	@Override
	public Location getLocationById(Long id) {

		if (locationDao.existsById(id)) {
			return locationDao.findById(id).orElse(null);
		}
		throw new InexistantLocationException("Cette réservation n'existe pas en base.");
	}

	@Override
	public void traitementLocationById(Long id, TraitementLocationDto traitementLocationDto) {

		Location currentLocation = getLocationById(id);
		Statut currentLocationStatut = statutService.getStatut(traitementLocationDto.getIdStatut());
		List<Parasol> listParasols = new ArrayList<>();

		if (currentLocation != null) {

			if (currentLocationStatut.getNom().equals("refusée")) {
				currentLocation.setStatut(currentLocationStatut);
				locationDao.save(currentLocation);

			} else {
				traitementLocationDto.getIdsParasol().forEach(idParasol -> {
					listParasols.add(parasolService.getParasol(idParasol));
				});
				currentLocation.setStatut(currentLocationStatut);
				currentLocation.setParasols(listParasols);
				locationDao.save(currentLocation);
			}

		}

	}

}
