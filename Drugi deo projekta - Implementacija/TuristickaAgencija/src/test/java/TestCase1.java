
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import projekat.Klijent;
import projekat.KlijentServiceImpl;

import static org.junit.Assert.*;

import java.util.Calendar;


public class TestCase1 {

	private static KlijentServiceImpl service;

	@BeforeClass
	public static void initTest() {
		service = new KlijentServiceImpl();
		System.out.println("BeforeClass: Uspesno inicijalizovan test");
	}

	// Preduslov: Ne postoji klijent sa id-om 1000
	@Before
	public void testPrecondition() {
		Klijent k = service.vratiKlijenta(1000);
		assertNull(k);
		Klijent k1 = service.vratiKlijenta("aleksa@gmail.com");
		assertNull(k1);
		System.out.println("Before: Preduslov");
	}

	// Test: Dodajemo klijenta sa id-jem 1000
	@Test
	public void testAssertions() {
		Calendar c = Calendar.getInstance();
		c.set(2002, 10, 23);
		service.dodajKlijenta(1000, "Aleksa", "Ilic", c.getTime(), "060000000", "aleksa@gmail.com");
		System.out.println("Test: Izvrsen");
	}

	// Post-uslov:Postoji zaposleni sa id-om 1000
	@After
	public void testPostcondition() {
		Klijent k = service.vratiKlijenta(1000);
		assertNotNull(k);
		System.out.println(service.prikaziKlijenta("aleksa@gmail.com"));
		System.out.println("After: Post-uslov");
	}

	// Brisemo test podatke iz baze
	@AfterClass
	public static void clearTest() {
		service.obrisiKlijenta(1000);
		System.out.println("AfterClass: Obrisani test podaci");
	}

}
