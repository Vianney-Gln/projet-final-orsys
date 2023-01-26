package com.orsys.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UtilisateurDto {

	private String prenom;

	private String nom;

	private String role = "utilisateur";

}
