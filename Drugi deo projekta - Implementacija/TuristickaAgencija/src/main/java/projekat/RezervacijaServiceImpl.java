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
		
	}
	
	public RezervacijaServiceImpl(EntityManager em) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgencijaPU");
		em = emf.createEntityManager();
	}

	
	@Override
	public void dodajRezervaciju(int id, int klijent_id, int aranzman_id, Date vremeRezervacije) {
		em.getTransaction().begin();
		Rezervacija r = new Rezervacija(id, klijent_id, aranzman_id, vremeRezervacije);
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public void obrisiRezervaciju(int id) {
		Rezervacija r = em.find(Rezervacija.class, id);
    	em.getTransaction().begin();
    	em.remove(r);
    	em.getTransaction().commit();
	}

}
