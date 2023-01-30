package com.orsys.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TraitementLocationDto {

	List<Long> idsParasol;
	@NotNull
	Long idStatut;

}
