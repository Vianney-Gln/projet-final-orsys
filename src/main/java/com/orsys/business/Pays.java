package com.orsys.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Pays {

	@Id
	private String code;
	private String nom;

	@JsonIgnore
	@OneToMany(mappedBy = "pays")
	private List<Locataire> locataires;

}
