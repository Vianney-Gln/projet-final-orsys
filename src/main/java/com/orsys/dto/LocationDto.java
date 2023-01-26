package com.orsys.dto;

import java.time.LocalDateTime;

import javax.persistence.Lob;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LocationDto {

	private LocalDateTime dateHeureDebut;
	private LocalDateTime dateHeureFin;
	private double montantEnEuros;
	@Lob
	private String remarques;
	private Long idLocataire;
	private Long idConcessionnaire;
	private Long idStatut = 1L;

}
