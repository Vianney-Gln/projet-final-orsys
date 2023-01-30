package com.orsys.services;

import java.util.List;

import com.orsys.business.File;
import com.orsys.business.Parasol;

public interface IParasolService {

	Parasol addParasol(Parasol parasol);

	Parasol getParasol(Long id);

	// Le parasol dont l'emplacement vaut -1
	Parasol getReservationParasolByFile(File file);

	// Liste des parasols par files
	List<Parasol> getParasolsByFile(File file);

}
