package com.ejb.services;

import com.jpa.entities.Klijent;

public interface KlijentService {
	public void dodajKlijenta(Klijent k);
	public void obrisiKlijenta(int id);
	public String prikaziKlijenta(String emailAdresa);
	public void azurirajEmailAdresu(int id, String emailAdresa);
	public void azurirajBrojTelefona(int id, String brojTelefona);
	public String prikaziSveKlijente();
}
