package com.orsys.dto;

import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.orsys.business.LienDeParente;
import com.orsys.business.Pays;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LocataireDto {

	private String nom;
	private String prenom;
	private String email;
	private Pays pays;
	@Size(min = 8, message = "Votre mot de passe doit comporter 8 caractères ou plus.")
	private String password;
	@Nullable
	private LienDeParente lienDeParente;

}
