package com.gui.controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.ejb.services.AranzmanService;
import com.jpa.entities.Aranzman;

@ManagedBean
public class AranzmanController {

	private Aranzman ar = new Aranzman();
	private int deleteId;
	private int findId;
	private boolean prikaziAranzmanBool;
	private int spisakId;
	private boolean prikaziSpisakBool;
	private int filter=7;
	private boolean prikaziFilterBool;
	private int updateId;
	private String opis;
 
    @EJB
	private AranzmanService service;


	public Aranzman getAr() {
		return ar;
	}

	public void setAr(Aranzman ar) {
		this.ar = ar;
	}
	
	public void dodajAranzman(Aranzman ar) {
		service.dodajAranzman(ar);
	}
	
	public void obrisiAranzman(int id) {
		service.obrisiAranzman(id);
	}
	
	public String prikaziAranzman(int id) {
		return service.prikaziAranzman(id);
	}

	public int getDeleteId() {
		return deleteId;
	}

	public void setDeleteId(int deleteId) {
		this.deleteId = deleteId;
	}

	public int getFindId() {
		return findId;
	}

	public void setFindId(int findId) {
		this.findId = findId;
	}

	public boolean isPrikaziAranzmanBool() {
		return prikaziAranzmanBool;
	}
	
	public void setPrikaziAranzmanBool(boolean prikaziAranzmanBool) {
		this.prikaziAranzmanBool = prikaziAranzmanBool;
	}
	
	public void postaviAranzmanBool() {
		prikaziAranzmanBool = true;
	}
	
	public String prikaziSpisakAranzmana(int id) {
		return service.prikaziSpisakRezervisanogAranzmana(id);
	}
	
	public int getSpisakId() {
		return spisakId;
	}

	public void setSpisakId(int spisakId) {
		this.spisakId = spisakId;
	}

	public boolean isPrikaziSpisakBool() {
		return prikaziSpisakBool;
	}

	public void setPrikaziSpisakBool(boolean prikaziSpisakBool) {
		this.prikaziSpisakBool = prikaziSpisakBool;
	}
	public void postaviSpisakBool() {
		prikaziSpisakBool = true;
	}

	public boolean isPrikaziFilterBool() {
		return prikaziFilterBool;
	}

	public void setPrikaziFilterBool(boolean prikaziFilterBool) {
		this.prikaziFilterBool = prikaziFilterBool;
	}
	
	public void postaviFilterBool() {
		prikaziFilterBool = true;
	}

	public int getFilter() {
		return filter;
	}

	public void setFilter(int filter) {
		this.filter = filter;
	}
	
	public String prikaziFiltriraneAranzmane(int brDana) {
		return service.prikaziAranzmaneNarednihNdana(brDana);
	}
	
	public void azurirajOpisAranzmana(int id, String opis) {
		service.azurirajOpisAranzmana(id, opis);
	}

	public int getUpdateId() {
		return updateId;
	}

	public void setUpdateId(int updateId) {
		this.updateId = updateId;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String prikaziNajpoznatijiAranzman() {
		return service.prikaziNajpoznatijiAranzman();
	}
}