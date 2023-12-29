package projekat;

import java.util.Date;

public interface RezervacijaService {
	public void dodajRezervaciju(int id, int klijent_id, int aranzman_id, Date vremeRezervacije);
	public void obrisiRezervaciju(int id);
}
