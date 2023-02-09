package com.orsys.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.orsys.business.File;
import com.orsys.business.Location;
import com.orsys.business.Parasol;
import com.orsys.dao.IParasolDao;
import com.orsys.services.IParasolService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ParasolServiceImpl implements IParasolService {

	private IParasolDao parasolDao;
	private FileServiceImpl fileService;

	@Override
	public Parasol addParasol(Parasol parasol) {

		return parasolDao.save(parasol);
	}

	@Override
	public Parasol getParasol(Long id) {

		return parasolDao.findById(id).orElse(null);
	}

	@Override
	public Parasol getReservationParasolByFile(File file) {
		Parasol parasol = new Parasol();
		parasol.setNumeroEmplacement((byte) -1);
		parasol.setFile(file);
		Example<Parasol> parasolExample = Example.of(parasol);
		return parasolDao.findOne(parasolExample).orElse(null);
	}

	/**
	 * Renvoie une map clé id de file et valeur list de parasols Verifie si un
	 * parasol possède déjà une réservation avant de l'envoyer dans la map
	 */

	@Override
	public Map<Long, List<Parasol>> getParasolsByFile(List<Long> fileIds, String dateStr) {
		Map<Long, List<Parasol>> parasolsByFile = new HashMap<>();
		List<File> currentFiles = fileService.findByFileIds(fileIds);
		LocalDateTime currDate = LocalDateTime.parse(dateStr);

		currentFiles.forEach(file -> {
			List<Parasol> listParasol = new ArrayList<>();

			for (int i = 0; i <= 8; i++) {
				Parasol parasol = new Parasol();
				parasol.setFile(file);
				parasol.setNumeroEmplacement((byte) i);
				Example<Parasol> parasolExample = Example.of(parasol);
				Parasol currParasol = parasolDao.findOne(parasolExample).orElse(null);
				List<Location> currParasolLocations = currParasol.getLocations();

				if (!currParasolLocations.isEmpty()) {
					currParasolLocations.forEach(currParasLoc -> {

						if (!currParasLoc.getDateHeureDebut().equals(currDate)) {

							listParasol.add(currParasol);

						}
					});
				} else {
					listParasol.add(currParasol);

				}

			}
			parasolsByFile.put(file.getId(),
					listParasol.stream().filter(para -> para.getNumeroEmplacement() != -1).toList());
		});

		return parasolsByFile;

	}

}
