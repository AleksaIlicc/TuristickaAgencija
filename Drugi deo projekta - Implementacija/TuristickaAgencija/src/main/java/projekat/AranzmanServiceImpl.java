package projekat;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
		return "Id: " + id + ", Naziv: " + a.getNaziv() + ", Cena: " + a.getCena() + ", Mesta(slobodna/ukupno): "
				+ a.getBrojSlobodnihMesta() + "/" + a.getBrojMesta() + ", Polazna destinacija= "
				+ a.getDolaznaDestinacija() + ", Vreme polaska: " + a.getVremePolaska() + ", Dolazna destinacija: "
				+ a.getDolaznaDestinacija() + ", Vreme dolaska: " + a.getVremeDolaska() + ", Info: " + a.getInfo()
				+ "\n";
	}

	@Override
	public String prikaziSpisakRezervisanogAranzmana(int id) {

		String rezultat = "";
		String jpqlQuery = "SELECT a.ime, a.prezime, a.brojTelefona, a.emailAdresa " + "FROM Aranzman a "
				+ "JOIN Rezervacija r ON a.id = r.aranzman.id " + "JOIN Klijent k ON k.id = r.klijent.id"
				+ "WHERE a.id = " + id;

		Query query = em.createQuery(jpqlQuery);

		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();

		for (Object[] result : resultList) {
			String ime = (String) result[0];
			String prezime = (String) result[1];
			String brojTelefona = (String) result[2];
			String emailAdresa = (String) result[3];

			rezultat = rezultat + "Ime: " + ime + ", Prezime: " + prezime + ", Broj telefona: " + brojTelefona
					+ ", Email adresa: " + emailAdresa + "\n";
		}
		return rezultat;
	}

	public String prikaziAranzmaneNarednihNdana(int brojDana) {
		String rezultat = "";

		TypedQuery<Aranzman> query = em.createQuery(
				"SELECT * FROM aranzmani" + "WHERE datediff(vremeDolaska, now()) <= " + brojDana, Aranzman.class);
		List<Aranzman> sviAranzmani = query.getResultList();

		for (Aranzman a : sviAranzmani) {
			rezultat = rezultat + "ID: " + a.getId() + "," + "Naziv: " + a.getNaziv() + ", Cena: " + a.getCena()
					+ ", Mesta(slobodna/ukupno): " + a.getBrojSlobodnihMesta() + "/" + a.getBrojMesta()
					+ ", Polazna destinacija: " + a.getPolaznaDestinacija() + ", Vreme polaska: " + a.getVremePolaska()
					+ ", Dolazna destinacija: " + a.getDolaznaDestinacija() + ", Vreme dolaska: " + a.getVremeDolaska()
					+ ", Opis: " + a.getInfo() + "\n";
		}
		return rezultat;
	}

	@Override
	public void azurirajOpisAranzmana(int id, String opis) {
		Aranzman a = em.find(Aranzman.class, id);
		if (a == null)
			return;

		em.getTransaction().begin();
		a.setInfo(opis);
		em.getTransaction().commit();
	}

	@Override
	public String prikaziNajpoznatijiAranzman() {
		String rezultat = "";

		TypedQuery<Aranzman> query = em.createQuery(
				"SELECT * FROM aranzmani" + "ORDER BY brojMesta-brojSlobodnihMesta DESC LIMIT 1", Aranzman.class);
		query.setMaxResults(1);
		Aranzman a = query.getSingleResult();

		rezultat = rezultat + "ID: " + a.getId() + "," + "Naziv: " + a.getNaziv() + ", Cena: " + a.getCena()
				+ ", Mesta(slobodna/ukupno): " + a.getBrojSlobodnihMesta() + "/" + a.getBrojMesta()
				+ ", Polazna destinacija: " + a.getPolaznaDestinacija() + ", Vreme polaska: " + a.getVremePolaska()
				+ ", Dolazna destinacija: " + a.getDolaznaDestinacija() + ", Vreme dolaska: " + a.getVremeDolaska()
				+ ", Opis: " + a.getInfo() + "\n";

		return rezultat;
	}

}
