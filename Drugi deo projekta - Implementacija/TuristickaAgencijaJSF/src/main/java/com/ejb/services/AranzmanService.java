package com.ejb.services;

import com.jpa.entities.Aranzman;

public interface AranzmanService {
	public void dodajAranzman(Aranzman a);

	public void obrisiAranzman(int id);

	public String prikaziAranzman(int id);

	public String prikaziSpisakRezervisanogAranzmana(int id);

	public String prikaziAranzmaneNarednihNdana(int brojDana);

	public void azurirajOpisAranzmana(int id, String opis);

	public String prikaziNajpoznatijiAranzman();
}
