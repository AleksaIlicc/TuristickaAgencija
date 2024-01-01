import static org.junit.Assert.assertNull;

import java.sql.Date;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import projekat.Aranzman;
import projekat.AranzmanServiceImpl;

public class TestCase3 {
	private static AranzmanServiceImpl service;

	@BeforeClass
	public static void initTest() {
		service = new AranzmanServiceImpl();
		System.out.println("BeforeClass: Uspesno inicijalizovan test");
	}

	// Preduslov: Ne postoje aranzmani sa id-evima 1000,1001 i 1002
	@Before
	public void testPrecondition() {
		Aranzman a1 = service.vratiAranzman(1000);
		assertNull(a1);
		Aranzman a2 = service.vratiAranzman(1001);
		assertNull(a2);
		Aranzman a3 = service.vratiAranzman(1002);
		assertNull(a3);
		
		System.out.println("Before: Preduslov");
	}

	// Test: Dodajemo aranzmane sa id-evima 1000,1001 i 1002, testiramo funkciju prikaziAranzmaneNarednihNdana(int brojDana)
	@Test
	public void testAssertions() {
		service.dodajAranzman(1000, "Oslo Tura 1", 300, 20, "Novi Sad", Date.valueOf("2024-1-29"), "Oslo", Date.valueOf("2024-2-1"), "Opis1");
		service.dodajAranzman(1001, "Oslo Tura 2", 300, 20, "Subotica", Date.valueOf("2024-1-19"), "Oslo", Date.valueOf("2024-1-22"), "Opis2");
		service.dodajAranzman(1002, "Oslo Tura 3", 300, 20, "Novi Sad", Date.valueOf("2024-2-15"), "Oslo", Date.valueOf("2024-2-17"), "Opis3");

		System.out.println(service.prikaziAranzmaneNarednihNdana(31));
		System.out.println("Test: Izvrsen");
	}

	// Brisemo test podatke iz baze
	@AfterClass
	public static void clearTest() {
		service.obrisiAranzman(1000);
		service.obrisiAranzman(1001);
		service.obrisiAranzman(1002);
		System.out.println("AfterClass: Obrisani test podaci");
	}
}
