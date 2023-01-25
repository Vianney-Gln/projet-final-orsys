package com.orsys.business;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Concessionnaire extends Utilisateur {

	private String numeroTelephone;

}
