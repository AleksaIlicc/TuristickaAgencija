package projekat;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Stateless
@Remote(AranzmanService.class)
public class AranzmanServiceImpl implements AranzmanService {

	private EntityManager em;

	public AranzmanServiceImpl() {
	}

	public AranzmanServiceImpl(EntityManager em) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgencijaPU");
		em = emf.createEntityManager();
	}

	@Override
	public void dodajAranzman(int id, String naziv, double cena, int brojMesta, String polaznaDestinacija,
			Date vremePolaska, String dolaznaDestinacija, Date vremeDolaska, String info) {
		em.getTransaction().begin();
		Aranzman a = new Aranzman(id, naziv, cena, brojMesta, polaznaDestinacija, vremePolaska, dolaznaDestinacija,
				vremeDolaska, info);
		em.persist(a);
		em.getTransaction().commit();

	}

	@Override
	public void obrisiAranzman(int id) {
		Aranzman a = em.find(Aranzman.class, id);
		if (a == null)
			return;
		em.getTransaction().begin();
		em.remove(a);
		em.getTransaction().commit();
	}

	@Override
	public String prikaziAranzman(int id) {
		Aranzman a = em.find(Aranzman.class, id);
		if (a == null)
			return "Ne postoji aranzman sa id-em " + id;
		return "Id: " + id + ", Naziv: " + a.getNaziv() + ", Cena: " + a.getCena() + ", Broj mesta: " + a.getBrojMesta()
				+ ", Polazna destinacija= " + a.getDolaznaDestinacija() + ", Vreme polaska: " + a.getVremePolaska()
				+ ", Dolazna destinacija: " + a.getDolaznaDestinacija() + ", Vreme dolaska: " + a.getVremeDolaska()
				+ ", Info: " + a.getInfo() + "\n";
	}

	@Override
	public String prikaziSpisakRezervacijaAranzmana(int id) {
		
		String rezultat = "";
		String jpqlQuery = "SELECT a.ime, a.prezime, a.brojTelefona, a.emailAdresa " +
		                 "FROM Aranzman a " +
		                 "JOIN Rezervacija r ON a.id = r.aranzman.id " +
		                 "JOIN Klijent k ON k.id = r.klijent.id" +
		                 "WHERE a.id = " + id;
		
		Query query = em.createQuery(jpqlQuery);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();
		
		for (Object[] result : resultList) {
		  String ime = (String) result[0];
		  String prezime = (String) result[1];
		  String brojTelefona = (String) result[2];
		  String emailAdresa = (String) result[3];
		
		  rezultat = rezultat + "Ime: " + ime + ", Prezime: " + prezime + ", Broj telefona: " + brojTelefona + ", Email adresa: " + emailAdresa + "\n";
		}
		return rezultat;
	}
}
