package com.orsys.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DemandeReservationDto {
	@NotNull
	private String dateHeureDebut;
	@NotNull
	private String dateHeureFin;
	@NotNull
	private Long idLocataire;
	@Nullable
	private String remarques;
	@NotEmpty
	private List<RequestedFileDto> requestedFiles;
}
