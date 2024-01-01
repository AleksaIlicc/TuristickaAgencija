package projekat;

import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateless
@Remote(RezervacijaService.class)
public class RezervacijaServiceImpl implements RezervacijaService {

	private EntityManager em;

	public RezervacijaServiceImpl() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgencijaPU");
		em = emf.createEntityManager();
	}

	@Override
	public void dodajRezervaciju(int id, int klijent_id, int aranzman_id, Date vremeRezervacije) {
		Aranzman a = em.find(Aranzman.class, aranzman_id);
		em.getTransaction().begin();
		int trBrMesta = a.getBrojSlobodnihMesta();
		if (trBrMesta <= 0) {
			return;
		}
		a.setBrojSlobodnihMesta(trBrMesta - 1);
		em.getTransaction().commit();

		em.getTransaction().begin();
		Rezervacija r = new Rezervacija(id, klijent_id, aranzman_id, vremeRezervacije);
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public void obrisiRezervaciju(int id) {
		Rezervacija r = em.find(Rezervacija.class, id);
		em.getTransaction().begin();
		int arId = r.getAranzman_id();
		if (r != null) {
			Aranzman a = em.find(Aranzman.class, arId);
			int brTrenutnihMesta = a.getBrojSlobodnihMesta();
			a.setBrojSlobodnihMesta(brTrenutnihMesta + 1);
		}
		em.getTransaction().commit();


		em.getTransaction().begin();
		em.remove(r);
		em.getTransaction().commit();
	}

	@Override
	public Rezervacija vratiRezervaciju(int id) {
		return em.find(Rezervacija.class, id);
	}

}
