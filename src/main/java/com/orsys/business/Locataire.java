package com.orsys.business;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Locataire extends Utilisateur {

	private LocalDateTime dateHeureInscription;

	@ManyToOne
	private Pays pays;

	@JsonIgnore
	@OneToMany(mappedBy = "locataire")
	private List<Location> locations;

	@ManyToOne
	private LienDeParente lienDeParente;

}
