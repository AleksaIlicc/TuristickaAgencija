package com.ejb.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ejb.services.KlijentService;
import com.jpa.entities.Klijent;

@Stateless
public class KlijentServiceImpl implements KlijentService {
	@PersistenceContext(name = "TuristickaAgencijaJSF")
	private EntityManager em;

	@Override
	public void dodajKlijenta(Klijent k) {
		Klijent emailTest = vratiKlijenta(k.getEmailAdresa());
		if (emailTest != null) {
			return;
		}
		em.persist(k);
	}

	@Override
	public void obrisiKlijenta(int id) {
		Klijent k = em.find(Klijent.class, id);
		if (k == null) {
			return;
		}
		em.remove(k);
	}

	@Override
	public String prikaziKlijenta(String emailAdresa) {
		String rezultat = "";
		Klijent k = vratiKlijenta(emailAdresa);
		if (k == null) {
			System.out.println("Ne postoji klijent sa email adresom: " + emailAdresa);
			return null;
		}
		return rezultat + "ID: " + k.getId() + "<br/>Ime: " + k.getIme() + "<br/>Prezime: " + k.getPrezime()
				+ "<br/>Datum rodjenja: " + k.getDatumRodjenja() + "<br/>Broj telefona: " + k.getBrojTelefona()
				+ "<br/>Email adresa: " + k.getEmailAdresa() + "<br/><br/>";
	}

	@Override
	public void azurirajEmailAdresu(int id, String emailAdresa) {
		Klijent k = em.find(Klijent.class, id);
		if (k == null)
			return;

		k.setEmailAdresa(emailAdresa);
	}

	@Override
	public void azurirajBrojTelefona(int id, String brojTelefona) {
		Klijent k = em.find(Klijent.class, id);
		if (k == null)
			return;

		k.setBrojTelefona(brojTelefona);
	}

	@Override
	public String prikaziSveKlijente() {
		String rezultat = "";

		TypedQuery<Klijent> query = em.createQuery("select k from Klijent k", Klijent.class);
		List<Klijent> sviKlijenti = query.getResultList();

		for (Klijent k : sviKlijenti) {
			rezultat = rezultat + "ID: " + k.getId() + "<br/>Ime: " + k.getIme() + "<br/>Prezime: " + k.getPrezime()
					+ "<br/>Datum rodjenja: " + k.getDatumRodjenja() + "<br/>Broj telefona: " + k.getBrojTelefona()
					+ "<br/>Email adresa: " + k.getEmailAdresa() + "<br/><br/>";
		}
		return rezultat;
	}

	public Klijent vratiKlijenta(String emailAdresa) {
		try {
			String hql = "select k from Klijent k WHERE emailAdresa = :email";
			TypedQuery<Klijent> query = em.createQuery(hql, Klijent.class);
			query.setParameter("email", emailAdresa);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
