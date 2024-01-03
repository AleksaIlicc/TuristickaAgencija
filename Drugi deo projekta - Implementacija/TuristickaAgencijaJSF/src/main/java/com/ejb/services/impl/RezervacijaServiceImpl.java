package com.ejb.services.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ejb.services.RezervacijaService;
import com.jpa.entities.Aranzman;
import com.jpa.entities.Rezervacija;

@Stateless
public class RezervacijaServiceImpl implements RezervacijaService{
	@PersistenceContext(name = "TuristickaAgencijaJSF")
	private EntityManager em;
	
	
	@Override
	public void dodajRezervaciju(Rezervacija r) {
		Aranzman a = em.find(Aranzman.class, r.getAranzman_id());
		int trBrSlMesta = a.getBrojSlobodnihMesta();
		if (trBrSlMesta <= 0) {
			return;
		}
		a.setBrojSlobodnihMesta(trBrSlMesta - 1);
		
		em.persist(r);
	}

	@Override
	public void obrisiRezervaciju(int id) {
		Rezervacija r = em.find(Rezervacija.class, id);
		int arId = r.getAranzman_id();
		if (r != null) {
			Aranzman a = em.find(Aranzman.class, arId);
			int brTrenutnihMesta = a.getBrojSlobodnihMesta();
			a.setBrojSlobodnihMesta(brTrenutnihMesta + 1);
		}
		em.remove(r);
	}

}
