package projekat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rezervacije")
public class Rezervacija {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "klijent_id")
	private int klijent_id;

	@Column(name = "aranzman_id")
	private int aranzman_id;

	@Column(name = "vremeRezervacije")
	private Date vremeRezervacije;
	@Column(name = "status")
	private String status;

	public Rezervacija() {
	}

	public Rezervacija(int id, int klijent_id, int aranzman_id, Date vremeRezervacije, String status) {
		this.id = id;
		this.klijent_id = klijent_id;
		this.aranzman_id = aranzman_id;
		this.vremeRezervacije = vremeRezervacije;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getVremeRezervacije() {
		return vremeRezervacije;
	}

	public void setVremeRezervacije(Date vremeRezervacije) {
		this.vremeRezervacije = vremeRezervacije;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
