package com.gui.controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.ejb.services.KlijentService;
import com.jpa.entities.Klijent;



@ManagedBean
public class KlijentController {

	private Klijent kl = new Klijent();
	private int deleteId;
	private int aIdEmail;
	private String aMailAdresa;
	private int aIdTel;
	private String aBrojTelefona;
	
	private String emailAdresa;
	
	@EJB
	private KlijentService service;

	public Klijent getKl() {
		return kl;
	}

	public void setKl(Klijent kl) {
		this.kl = kl;
	}

	public int getDeleteId() {
		return deleteId;
	}

	public void setDeleteId(int deleteId) {
		this.deleteId = deleteId;
	}
	
	public void dodajKlijenta(Klijent k) {
		service.dodajKlijenta(k);
	}
	
	public void obrisiKlijenta(int id) {
		service.obrisiKlijenta(id);
	}
	
	public String prikaziKlijenta(String emailAdresa) {
		return service.prikaziKlijenta(emailAdresa);
	}

	public String getEmailAdresa() {
		return emailAdresa;
	}

	public void setEmailAdresa(String emailAdresa) {
		this.emailAdresa = emailAdresa;
	}
	
	public void azurirajEmailAdresu(int id, String adresa) {
		service.azurirajEmailAdresu(id, adresa);
	}
	
	public void azurirajBrojTelefona(int id, String brTel) {
		service.azurirajBrojTelefona(id, brTel);
	}

	public int getaIdEmail() {
		return aIdEmail;
	}

	public void setaIdEmail(int aIdEmail) {
		this.aIdEmail = aIdEmail;
	}

	public int getaIdTel() {
		return aIdTel;
	}

	public void setaIdTel(int aIdTel) {
		this.aIdTel = aIdTel;
	}

	public String getaMailAdresa() {
		return aMailAdresa;
	}

	public void setaMailAdresa(String aMailAdresa) {
		this.aMailAdresa = aMailAdresa;
	}

	public String getaBrojTelefona() {
		return aBrojTelefona;
	}

	public void setaBrojTelefona(String aBrojTelefona) {
		this.aBrojTelefona = aBrojTelefona;
	}
	
	public String prikaziSveKlijente() {
		return service.prikaziSveKlijente();
	}
	
}
