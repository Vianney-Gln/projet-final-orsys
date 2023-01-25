package com.orsys.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private byte numero;

	private Double prixJournalier;

	@OneToMany(mappedBy = "file")
	private List<Parasol> parasols;

}
