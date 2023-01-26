package com.orsys.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.orsys.business.Concessionnaire;
import com.orsys.business.Locataire;
import com.orsys.business.Location;
import com.orsys.business.Parasol;
import com.orsys.business.Statut;
import com.orsys.dao.ILocationDao;
import com.orsys.dto.LocationDto;
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
	private ILocationDao locationDao;

	@Override
	public LocationDto addLocation(LocationDto locationDto, Long... idParasols) {

		Concessionnaire concessionnaire = concessionnaireService.getConcessionnaire(locationDto.getIdConcessionnaire());
		Locataire locataire = locataireService.getLocataire(locationDto.getIdLocataire());
		Statut statut = statutService.getStatut(locationDto.getIdStatut());
		Location location = locationMapper.toEntity(locationDto);
		List<Parasol> listParasols = new ArrayList<>();

		location.setConcessionnaire(concessionnaire);
		location.setLocataire(locataire);
		location.setStatut(statut);

		for (int i = 0; i < idParasols.length; i++) {
			Parasol parasol = parasolService.getParasol(idParasols[i]);
			listParasols.add(parasol);
		}

		location.setParasols(listParasols);

		return locationMapper.toDto(locationDao.save(location));
	}

}
