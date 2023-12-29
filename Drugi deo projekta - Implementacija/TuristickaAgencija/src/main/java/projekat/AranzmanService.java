package projekat;

import java.util.Date;

public interface AranzmanService {
	public void dodajAranzman(int id, String naziv, double cena, int brojMesta, String polaznaDestinacija, Date vremePolaska,
			String dolaznaDestinacija, Date vremeDolaska, String info);
	public void obrisiAranzman(int id);
	public String prikaziAranzman(int id);
	public String prikaziSpisakRezervacijaAranzmana(int id);
}
