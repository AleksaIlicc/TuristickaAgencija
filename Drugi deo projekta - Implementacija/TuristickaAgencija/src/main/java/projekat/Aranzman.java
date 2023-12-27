package projekat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aranzmani")
public class Aranzman {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "naziv")
	private String naziv;
	@Column(name = "cena")
	private double cena;
	@Column(name = "brojMesta")
	private int brojMesta;

	@Column(name = "polaznaDestinacija")
	private String polaznaDestinacija;
	@Column(name = "vremePolaska")
	private Date vremePolaska;
	@Column(name = "dolaznaDestinacija")
	private String dolaznaDestinacija;
	@Column(name = "vremeDolaska")
	private Date vremeDolaska;

	@Column(name = "info")
	private String info;
	@Column(name = "slika")
	private String slika;

	@Column(name = "brojDanaAktivnosti")
	private int brojDanaAktivnosti; // koliko je dana aranzman aktivan

	public Aranzman() {
	}

	public Aranzman(int id, String naziv, double cena, int brojMesta, String polaznaDestinacija, Date vremePolaska,
			String dolaznaDestinacija, Date vremeDolaska, String info, String slika, int brojDanaAktivnosti) {
		this.id = id;
		this.naziv = naziv;
		this.cena = cena;
		this.brojMesta = brojMesta;
		this.polaznaDestinacija = polaznaDestinacija;
		this.vremePolaska = vremePolaska;
		this.dolaznaDestinacija = dolaznaDestinacija;
		this.vremeDolaska = vremeDolaska;
		this.info = info;
		this.slika = slika;
		this.brojDanaAktivnosti = brojDanaAktivnosti;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	public String getPolaznaDestinacija() {
		return polaznaDestinacija;
	}

	public void setPolaznaDestinacija(String polaznaDestinacija) {
		this.polaznaDestinacija = polaznaDestinacija;
	}

	public Date getVremePolaska() {
		return vremePolaska;
	}

	public void setVremePolaska(Date vremePolaska) {
		this.vremePolaska = vremePolaska;
	}

	public String getDolaznaDestinacija() {
		return dolaznaDestinacija;
	}

	public void setDolaznaDestinacija(String dolaznaDestinacija) {
		this.dolaznaDestinacija = dolaznaDestinacija;
	}

	public Date getVremeDolaska() {
		return vremeDolaska;
	}

	public void setVremeDolaska(Date vremeDolaska) {
		this.vremeDolaska = vremeDolaska;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public int getBrojDanaAktivnosti() {
		return brojDanaAktivnosti;
	}

	public void setBrojDanaAktivnosti(int brojDanaAktivnosti) {
		this.brojDanaAktivnosti = brojDanaAktivnosti;
	}
}
