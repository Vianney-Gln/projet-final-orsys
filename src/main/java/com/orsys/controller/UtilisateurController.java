package com.orsys.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orsys.auth.Credentials;
import com.orsys.business.Concessionnaire;
import com.orsys.business.Locataire;
import com.orsys.business.Utilisateur;
import com.orsys.dto.LocataireDto;
import com.orsys.dto.UtilisateurDto;
import com.orsys.exceptions.UtilisateurAlreadyExistException;
import com.orsys.exceptions.WrongCredentialsException;
import com.orsys.mapper.UtilisateurMapper;
import com.orsys.services.impl.LocataireServiceImpl;
import com.orsys.services.impl.UtilisateurServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {

	private UtilisateurServiceImpl utilisateurService;
	private UtilisateurMapper utilisateurMapper;
	private LocataireServiceImpl locataireService;

	@PostMapping("login")
	UtilisateurDto getUser(@RequestBody Credentials credentials) throws WrongCredentialsException {
		String role = "";
		Utilisateur utilisateur = utilisateurService.getCurrentUser(credentials.getEmail());

		if (utilisateur != null) {
			if (utilisateur instanceof Locataire) {
				role = "locataire";
			} else if (utilisateur instanceof Concessionnaire) {
				role = "concessionnaire";
			}

			if (credentials.getPassword().equals(utilisateur.getMotDePasse())) {
				UtilisateurDto utilisateurDto = utilisateurMapper.toDto(utilisateur);
				utilisateurDto.setRole(role);
				utilisateurDto.setId(utilisateur.getId());
				return utilisateurDto;
			} else {
				throw new WrongCredentialsException("Mauvais credentials");
			}

		} else {
			throw new WrongCredentialsException("Mauvais credentials");
		}
	}

	@PostMapping("inscription")
	LocataireDto addNewUser(@RequestBody LocataireDto locataireDto) {

		return locataireService.addLocataire(locataireDto);
	}

	// EXCEPTIONS

	@ExceptionHandler(WrongCredentialsException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	public String traiterWrongCredentials(Exception exception) {
		return exception.getMessage();
	}

	@ExceptionHandler(UtilisateurAlreadyExistException.class)
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	public String traiterUtilisateurAlreadyExist(Exception exception) {
		return exception.getMessage();
	}

}
