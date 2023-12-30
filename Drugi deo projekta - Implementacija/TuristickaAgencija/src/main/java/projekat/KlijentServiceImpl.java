package projekat;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@Stateless
@Remote(KlijentService.class)
public class KlijentServiceImpl implements KlijentService {

	private EntityManager em;

	public KlijentServiceImpl() {

	}

	public KlijentServiceImpl(EntityManager em) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgencijaPU");
		em = emf.createEntityManager();
	}

	@Override
	public void dodajKlijenta(int id, String ime, String prezime, Date datumRodjenja, String brojTelefona,
			String emailAdresa) {
		em.getTransaction().begin();
		Klijent k = new Klijent(id, ime, prezime, datumRodjenja, brojTelefona, emailAdresa);
		em.persist(k);
		em.getTransaction().commit();
	}

	@Override
	public void obrisiKlijenta(int id) {
		Klijent k = em.find(Klijent.class, id);
		if (k == null)
			return;

		em.getTransaction().begin();
		em.remove(k);
		em.getTransaction().commit();
	}

	@Override
	public void azurirajEmailAdresu(int id, String emailAdresa) {
		Klijent k = em.find(Klijent.class, id);
		if (k == null)
			return;

		em.getTransaction().begin();
		k.setEmailAdresa(emailAdresa);
		em.getTransaction().commit();
	}

	@Override
	public void azurirajBrojTelefona(int id, String brojTelefona) {
		Klijent k = em.find(Klijent.class, id);
		if (k == null)
			return;

		em.getTransaction().begin();
		k.setBrojTelefona(brojTelefona);
		em.getTransaction().commit();
	}

	@Override
	public String prikaziSveKlijente() {

		String rezultat = "";

		TypedQuery<Klijent> query = em.createQuery("select * from klijenti k", Klijent.class);
		List<Klijent> sviKlijenti = query.getResultList();

		for (Klijent k : sviKlijenti) {
			rezultat = rezultat + "ID: " + k.getId() + "," + "Ime: " + k.getIme() + ", Prezime: " + k.getPrezime()
					+ ", Datum rodjenja: " + k.getDatumRodjenja() + ", Broj telefona: " + k.getBrojTelefona()
					+ ", Email adresa: " + k.getEmailAdresa() + "\n";
		}
		return rezultat;
	}

	@Override
	public String prikaziKlijenta(String emailAdresa) {
		String rezultat = "";
		Klijent k = (Klijent) em.createQuery("SELECT * FROM klijenti WHERE emailAdresa= " + emailAdresa, Klijent.class);
		if (k == null) {
			System.out.println("Ne postoji klijent sa email adresom: " + emailAdresa);
			return null;
		}
		return rezultat + "ID:" + k.getId() + "," + "Ime: " + k.getIme() + ", Prezime: " + k.getPrezime()
				+ ", Datum rodjenja: " + k.getDatumRodjenja() + ", Broj telefona: " + k.getBrojTelefona()
				+ ", Email adresa: " + k.getEmailAdresa() + "\n";
	}
}