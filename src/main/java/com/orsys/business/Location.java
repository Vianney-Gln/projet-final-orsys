package com.orsys.business;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dateHeureDebut;
	private LocalDateTime dateHeureFin;
	private double montantEnEuros;
	@Lob
	private String remarques;

	@ManyToOne
	private Locataire locataire;

	@NotNull
	@ManyToMany
	private List<Parasol> parasols;

	@ManyToOne
	private Concessionnaire concessionnaire;

	@ManyToOne
	private Statut statut;

}
