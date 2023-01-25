package com.orsys.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import io.micrometer.core.lang.Nullable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Parasol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private byte numeroEmplacement;

	@ManyToOne
	private File file;

	@ManyToMany(mappedBy = "parasols")
	@Nullable
	private List<Location> locations;

}
