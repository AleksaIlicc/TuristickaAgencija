package projekat;

import java.util.Date;

public interface KlijentService {
	public void dodajKlijenta(int id, String ime, String prezime, Date datumRodjenja, String brojTelefona, String emailAdresa);
	public void obrisiKlijenta(int id);
	public String prikaziKlijenta(String emailAdresa);
	public void azurirajEmailAdresu(int id, String emailAdresa);
	public void azurirajBrojTelefona(int id, String brojTelefona);
	public String prikaziSveKlijente();
	public Klijent vratiKlijenta(int id);
	public Klijent vratiKlijenta(String emailAdresa);
}
