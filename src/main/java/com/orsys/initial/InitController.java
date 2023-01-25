package com.orsys.initial;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.orsys.business.File;
import com.orsys.business.LienDeParente;
import com.orsys.business.Parasol;
import com.orsys.dao.IFileDao;
import com.orsys.dao.ILienDeParenteDao;
import com.orsys.dao.IParasolDao;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class InitController implements CommandLineRunner {

	private IFileDao fileDao;
	private IParasolDao parasolDao;
	private ILienDeParenteDao lienDeParenteDao;

	@Override
	public void run(String... args) throws Exception {
		ajoutFiles();
		ajoutLienDeParente();
		System.out.println("Initialisation ok");

	}

	/**
	 * Methode qui ajoute des files et des parasols
	 */
	void ajoutFiles() {

		int prix = 100;

		for (byte nbFile = 1; nbFile <= 8; nbFile++) {
			File file = new File();
			file.setPrixJournalier(prix);
			file.setNumero(nbFile);
			prix -= 7;
			file = fileDao.save(file);
			for (byte nbParasol = 0; nbParasol < 9; nbParasol++) {
				Parasol parasol = new Parasol();
				parasol.setNumeroEmplacement(nbParasol);
				parasol.setFile(file);
				parasolDao.save(parasol);
			}
		}
	}

	/**
	 * Ajout de 3 liens de parentés
	 */
	void ajoutLienDeParente() {

		LienDeParente lienDeParente1 = new LienDeParente();
		lienDeParente1.setNom("Frère ou Soeur");
		lienDeParente1.setCoefficient(30F);
		lienDeParenteDao.save(lienDeParente1);

		LienDeParente lienDeParente2 = new LienDeParente();
		lienDeParente2.setNom("Cousin ou Cousine");
		lienDeParente2.setCoefficient(20F);
		lienDeParenteDao.save(lienDeParente2);

		LienDeParente lienDeParente3 = new LienDeParente();
		lienDeParente3.setNom("Parent ou Enfant");
		lienDeParente3.setCoefficient(50F);
		lienDeParenteDao.save(lienDeParente3);

	}

}
