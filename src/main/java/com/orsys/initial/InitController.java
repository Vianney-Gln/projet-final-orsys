package com.orsys.initial;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.orsys.business.Concessionnaire;
import com.orsys.business.File;
import com.orsys.business.LienDeParente;
import com.orsys.business.Locataire;
import com.orsys.business.Parasol;
import com.orsys.business.Pays;
import com.orsys.business.Statut;
import com.orsys.dao.IConcessionnaireDao;
import com.orsys.dao.IFileDao;
import com.orsys.dao.ILienDeParenteDao;
import com.orsys.dao.ILocataireDao;
import com.orsys.dao.IParasolDao;
import com.orsys.dao.IPaysDao;
import com.orsys.dao.IStatutDao;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class InitController implements CommandLineRunner {

	private IFileDao fileDao;
	private IParasolDao parasolDao;
	private ILienDeParenteDao lienDeParenteDao;
	private IPaysDao paysDao;
	private IStatutDao statutDao;
	private IConcessionnaireDao concessionnaireDao;
	private ILocataireDao locataireDao;

	@Override
	public void run(String... args) throws Exception {
		ajoutFiles();
		ajoutLienDeParente();
		ajoutPays();
		ajoutStatut();
		ajoutConcessionnaire();
		ajoutLocataire();
		System.out.println("Initialisation ok");

	}

	/**
	 * Methode qui ajoute des files et des parasols
	 */
	void ajoutFiles() {

		List<File> listFiles = fileDao.findAll();

		if (listFiles.size() != 8) {
			int prix = 100;

			for (byte nbFile = 1; nbFile <= 8; nbFile++) {
				File file = new File();
				file.setPrixJournalier(prix);
				file.setNumero(nbFile);
				prix -= 7;
				file = fileDao.save(file);
				for (byte nbParasol = 1; nbParasol <= 10; nbParasol++) {
					Parasol parasol = new Parasol();
					parasol.setNumeroEmplacement(nbParasol);
					parasol.setFile(file);
					parasolDao.save(parasol);
				}
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

	/**
	 * Ajout de 5 pays.
	 */
	void ajoutPays() {

		Pays pays1 = new Pays();
		pays1.setCode("FR");
		pays1.setNom("France");
		paysDao.save(pays1);

		Pays pays2 = new Pays();
		pays2.setCode("IT");
		pays2.setNom("Italie");
		paysDao.save(pays2);

		Pays pays3 = new Pays();
		pays3.setCode("ES");
		pays3.setNom("Espagne");
		paysDao.save(pays3);

		Pays pays4 = new Pays();
		pays4.setCode("DEU");
		pays4.setNom("Allemagne");
		paysDao.save(pays4);

		Pays pays5 = new Pays();
		pays5.setCode("BEL");
		pays5.setNom("Belgique");
		paysDao.save(pays5);

	}

	/**
	 * Ajout de 3 statuts
	 */
	void ajoutStatut() {
		List<String> noms = Arrays.asList("A traiter", "Confirmée", "refusée");
		noms.forEach(nom -> {
			Statut statut = new Statut();
			statut.setNom(nom);
			statutDao.save(statut);
		});
	}

	/**
	 * Ajout concessionnaire
	 */
	void ajoutConcessionnaire() {
		Concessionnaire concessionnaire = new Concessionnaire();
		concessionnaire.setEmail("peppe@orsys.fr");
		concessionnaire.setMotDePasse("12345678");
		concessionnaire.setNumeroTelephone("+3912345678");
		concessionnaire.setNom("Peppe");
		concessionnaire.setPrenom("Mario");
		concessionnaireDao.save(concessionnaire);
	}

	/**
	 * Ajout Locataires
	 */
	void ajoutLocataire() {
		Locataire locataire = new Locataire();
		Pays pays = paysDao.findById("FR").orElse(null);
		locataire.setPays(pays);
		locataire.setEmail("test@gmail.com");
		locataire.setMotDePasse("12345678");
		locataire.setNom("Geloen");
		locataire.setPrenom("Vianney");
		locataire.setDateHeureInscription(LocalDateTime.now());
		locataireDao.save(locataire);

		Locataire locataire2 = new Locataire();
		locataire2.setPays(paysDao.findById("BEL").orElse(null));
		locataire2.setEmail("locataire2@gmail.com");
		locataire2.setMotDePasse("12345678");
		locataire2.setNom("Pignon");
		locataire2.setPrenom("François");
		locataire2.setDateHeureInscription(LocalDateTime.now());
		locataireDao.save(locataire2);

		Locataire locataire3 = new Locataire();
		locataire3.setPays(paysDao.findById("ES").orElse(null));
		locataire3.setEmail("locataire3@gmail.com");
		locataire3.setMotDePasse("12345678");
		locataire3.setNom("Aguilar");
		locataire3.setPrenom("Diego");
		locataire3.setDateHeureInscription(LocalDateTime.now());
		locataireDao.save(locataire3);

		Locataire locataire4 = new Locataire();
		locataire4.setPays(paysDao.findById("IT").orElse(null));
		locataire4.setEmail("locataire4@gmail.com");
		locataire4.setMotDePasse("12345678");
		locataire4.setNom("Rossi");
		locataire4.setPrenom("Leonardo");
		locataire4.setDateHeureInscription(LocalDateTime.now());
		locataireDao.save(locataire4);

	}

}
