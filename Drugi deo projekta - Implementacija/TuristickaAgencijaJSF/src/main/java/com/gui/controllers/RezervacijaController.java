package com.gui.controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.ejb.services.RezervacijaService;
import com.jpa.entities.Rezervacija;


@ManagedBean
public class RezervacijaController {

	private Rezervacija rez = new Rezervacija();
	private int deleteId;
	
	@EJB
	private RezervacijaService service;
	
	 public Rezervacija getRez() {
		return rez;
	}

	public void setRez(Rezervacija rez) {
		this.rez = rez;
	}

	public void dodajRezervaciju(Rezervacija r) {
		service.dodajRezervaciju(r);
	}

	public int getDeleteId() {
		return deleteId;
	}

	public void setDeleteId(int deleteId) {
		this.deleteId = deleteId;
	}
	
	public void obrisiRezervaciju(int id) {
		service.obrisiRezervaciju(id);
	}
	
}
