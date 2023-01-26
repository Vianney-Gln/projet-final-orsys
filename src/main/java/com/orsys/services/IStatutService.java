package com.orsys.services;

import com.orsys.business.Statut;

public interface IStatutService {

	Statut addStatut(Statut statut);

	Statut getStatut(Long id);

}
