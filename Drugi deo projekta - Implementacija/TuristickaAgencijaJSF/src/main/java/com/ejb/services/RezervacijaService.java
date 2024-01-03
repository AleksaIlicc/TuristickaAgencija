package com.ejb.services;

import com.jpa.entities.Rezervacija;

public interface RezervacijaService {
	public void dodajRezervaciju(Rezervacija r);
	public void obrisiRezervaciju(int id);
}
