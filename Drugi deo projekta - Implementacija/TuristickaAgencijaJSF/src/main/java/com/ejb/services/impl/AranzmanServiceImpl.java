package com.ejb.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ejb.services.AranzmanService;
import com.jpa.entities.*;

@Stateless
public class AranzmanServiceImpl implements AranzmanService {
	@PersistenceContext(name = "TuristickaAgencijaJSF")
	private EntityManager em;

	@Override
	public void dodajAranzman(Aranzman a) {
		try {
			a.setBrojSlobodnihMesta(a.getBrojMesta());
			em.persist(a);
		} catch (Exception e) {
			System.out.println("Greska prilikom rada sa bazom: \n" + e.getMessage());
		}
	}

	@Override
	public void obrisiAranzman(int id) {
		Aranzman a = em.find(Aranzman.class, id);
		em.remove(a);
	}

	@Override
	public String prikaziAranzman(int id) {
		Aranzman a = em.find(Aranzman.class, id);
		if (a == null)
			return "Ne postoji aranzman sa id-em " + id;
		return "Id: " + id + "<br/>Naziv: " + a.getNaziv() + "<br/>Cena: " + a.getCena()
				+ "<br/>Mesta(slobodna/ukupno): " + a.getBrojSlobodnihMesta() + "/" + a.getBrojMesta()
				+ "<br/>Polazna destinacija= " + a.getDolaznaDestinacija() + "<br/>Vreme polaska: "
				+ a.getVremePolaska() + "<br/>Dolazna destinacija: " + a.getDolaznaDestinacija()
				+ "<br/>Vreme dolaska: " + a.getVremeDolaska() + "<br/>Info: " + a.getInfo() + "<br/><br/>";
	}

	@Override
	public String prikaziSpisakRezervisanogAranzmana(int id) {

		Aranzman a = em.find(Aranzman.class, id);
		if (a == null) {
			return "Ne postoji aranzman sa id-em: " + id;
		}
		String rezultat = "";
		/*
		 * not working because Hibernate can't find Rezervacija and Klijent String
		 * jpqlQuery = "SELECT k.ime, k.prezime, k.brojTelefona, k.emailAdresa " +
		 * "FROM Aranzman a " + "JOIN Rezervacija r ON a.id = r.aranzman_id " +
		 * "JOIN Klijent k ON k.id = r.klijent_id " + "WHERE a.id = :aranzmanId";
		 */

		String jpqlQuery = "SELECT k.ime, k.prezime, k.brojTelefona, k.emailAdresa "
				+ "FROM Aranzman a, Rezervacija r, Klijent k " + "WHERE a.id = r.aranzman_id AND "
				+ "k.id = r.klijent_id AND " + "a.id = :aranzmanId";

		TypedQuery<Object[]> query = em.createQuery(jpqlQuery, Object[].class);
		query.setParameter("aranzmanId", id);

		List<Object[]> resultList = query.getResultList();

		for (Object[] result : resultList) {
			String ime = (String) result[0];
			String prezime = (String) result[1];
			String brojTelefona = (String) result[2];
			String emailAdresa = (String) result[3];

			rezultat = rezultat + "Ime: " + ime + "<br/>Prezime: " + prezime + "<br/>Broj telefona: " + brojTelefona
					+ "<br/>Email adresa: " + emailAdresa + "<br/><br/>";
		}
		return rezultat;
	}

	public String prikaziAranzmaneNarednihNdana(int brojDana) {
		String rezultat = "";

		TypedQuery<Aranzman> query = em.createQuery("SELECT a FROM Aranzman a "
				+ "WHERE datediff(vremePolaska, now()) <= " + brojDana + " AND now()<vremePolaska", Aranzman.class);
		List<Aranzman> sviAranzmani = query.getResultList();

		for (Aranzman a : sviAranzmani) {
			rezultat = rezultat + "ID: " + a.getId() + "<br/>Naziv: " + a.getNaziv() + "<br/>Cena: " + a.getCena()
					+ "<br/>Mesta(slobodna/ukupno): " + a.getBrojSlobodnihMesta() + "/" + a.getBrojMesta()
					+ "<br/>Polazna destinacija: " + a.getPolaznaDestinacija() + "<br/>Vreme polaska: " + a.getVremePolaska()
					+ "<br/>Dolazna destinacija: " + a.getDolaznaDestinacija() + "<br/>Vreme dolaska: " + a.getVremeDolaska()
					+ "<br/>Opis: " + a.getInfo() + "<br/><br/>";
		}
		return rezultat;
	}

	@Override
	public void azurirajOpisAranzmana(int id, String opis) {
		Aranzman a = em.find(Aranzman.class, id);
		if (a == null)
			return;
		a.setInfo(opis);
	}

	public String prikaziNajpoznatijiAranzman() {
		String rezultat = "";

		TypedQuery<Aranzman> query = em.createQuery(
				"SELECT a FROM Aranzman a " + "ORDER BY brojMesta-brojSlobodnihMesta DESC", Aranzman.class);

		query.setMaxResults(1);
		Aranzman a = query.getSingleResult();
		if (a == null) {
			return "Ne postoje aranžmani u bazi podataka.";
		}

		rezultat = rezultat + "ID: " + a.getId() + "<br/>Naziv: " + a.getNaziv() + "<br/>Cena: " + a.getCena()
				+ "<br/>Mesta(slobodna/ukupno): " + a.getBrojSlobodnihMesta() + "/" + a.getBrojMesta()
				+ "<br/>Polazna destinacija: " + a.getPolaznaDestinacija() + "<br/>Vreme polaska: " + a.getVremePolaska()
				+ "<br/>Dolazna destinacija: " + a.getDolaznaDestinacija() + "<br/>Vreme dolaska: " + a.getVremeDolaska()
				+ "<br/>Opis: " + a.getInfo() + "<br/>";

		return rezultat;
	}

}