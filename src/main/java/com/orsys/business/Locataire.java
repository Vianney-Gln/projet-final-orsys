package com.orsys.business;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Locataire extends Utilisateur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDateTime dateHeureInscription;

	@ManyToOne
	private Pays pays;

	@OneToMany(mappedBy = "locataire")
	private List<Location> locations;

	@ManyToOne
	private LienDeParente lienDeParente;

}
