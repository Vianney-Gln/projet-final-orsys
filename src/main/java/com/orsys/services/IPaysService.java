package com.orsys.services;

import java.util.List;

import com.orsys.business.Pays;

public interface IPaysService {

	List<Pays> getPays();

	Pays getPaysById(String id);

}
