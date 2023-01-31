package com.orsys.services;

import java.util.List;
import java.util.Map;

import com.orsys.business.File;
import com.orsys.business.Parasol;

public interface IParasolService {

	Parasol addParasol(Parasol parasol);

	Parasol getParasol(Long id);

	// Le parasol dont l'emplacement vaut -1
	Parasol getReservationParasolByFile(File file);

	// Map des parasols par idfiles
	Map<Long, List<Parasol>> getParasolsByFile(List<Long> fileIds);

}
