
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import projekat.RezervacijaServiceImpl;
import projekat.Aranzman;
import projekat.AranzmanServiceImpl;
import projekat.Klijent;
import projekat.KlijentServiceImpl;
import projekat.Rezervacija;

public class TestCase2 {

	private static KlijentServiceImpl serviceKlijent;
	private static RezervacijaServiceImpl serviceRezervacija;
	private static AranzmanServiceImpl serviceAranzman;

	@BeforeClass
	public static void initTest() {
		serviceRezervacija = new RezervacijaServiceImpl();
		serviceKlijent = new KlijentServiceImpl();
		serviceAranzman = new AranzmanServiceImpl();
		System.out.println("BeforeClass: Uspesno inicijalizovan test");
	}

	// Preduslov: Ne postoje klijent,aranzman i rezervacija sa id-em 1000
	@Before
	public void testPrecondition() {
		Rezervacija r = serviceRezervacija.vratiRezervaciju(1000);
		assertNull(r);
		Aranzman a = serviceAranzman.vratiAranzman(1000);
		assertNull(a);
		Klijent k = serviceKlijent.vratiKlijenta(1000);
		assertNull(k);
		Klijent k1 = serviceKlijent.vratiKlijenta("petarperic@gmail.com");
		assertNull(k1);
		System.out.println("Before: Preduslov");
	}

	// Test: Dodajemo klijenta, aranzman i rezervaciju
	@Test
	public void testAssertions() {
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal.set(2024, 06, 15);
		cal2.set(2024, 06, 25);
		serviceAranzman.dodajAranzman(1000, "Budva 2024", 150, 55, "Nis", cal.getTime(), "Budva", cal2.getTime(),
				"Testiranje opisa aranzmana Budva 2024.");
		
		cal.set(1988, 12, 16);
		serviceKlijent.dodajKlijenta(1000, "Petar", "Peric", cal.getTime(), "065252522", "petarperic@gmail.com");
		
		cal.set(2024, 1, 1);
		serviceRezervacija.dodajRezervaciju(1000, 1000, 1000, cal.getTime());
		System.out.println("Test: Izvrsen");
	}

	// Post-uslov: Postoji rezervacija sa id-em 1000
	@After
	public void testPostcondition() {
		Rezervacija r = serviceRezervacija.vratiRezervaciju(1000);
		assertNotNull(r);
		System.out.println("After: Post-uslov");
	}

	// Brisemo test podatke iz baze
	@AfterClass
	public static void clearTest() {
		serviceRezervacija.obrisiRezervaciju(1000);
		serviceAranzman.obrisiAranzman(1000);
		serviceKlijent.obrisiKlijenta(1000);
		System.out.println("AfterClass: Obrisani test podaci");
	}

}
