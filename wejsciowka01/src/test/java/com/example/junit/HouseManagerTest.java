package com.example.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class HouseManagerTest {


	HouseManager houseManager = new HouseManager();

	private final static String ULICA_1 = "Majowa";
	private final static boolean DOSTEPNY_1 = true;
	private final static int NUMER_1 = 1;

	@Test
	public void checkAdding(){

		int houseSizeBeforeAdd = 0;

		House drzewo = new House(ULICA_1, DOSTEPNY_1, NUMER_1);

		houseManager.addHouse(drzewo);

		int houseSizeAfterAdd = houseManager.houses.size();

		int housesSub = houseSizeAfterAdd - houseSizeBeforeAdd;

		assertEquals(1, housesSub);

		assertEquals(ULICA_1, houseManager.houses.get(houseManager.houses.size() - 1).getUlica());
		assertEquals(NUMER_1, houseManager.houses.get(houseManager.houses.size() - 1).getNumer());
		assertEquals(DOSTEPNY_1, houseManager.houses.get(houseManager.houses.size() - 1).getDostepny());

	}

	@Test
	public void checkRemoving(){

		int houseSizeBeforeAdd = 0;

		House drzewo = new House(ULICA_1, DOSTEPNY_1, NUMER_1);

		houseManager.addHouse(drzewo);

		int houseSizeAfterAdd = houseManager.houses.size();

		int housesSub = houseSizeAfterAdd - houseSizeBeforeAdd;

		assertEquals(1, housesSub);

		houseManager.removeHouse(drzewo);

		int houseSizeAfterRemove = houseManager.houses.size();

		assertEquals(0, houseSizeAfterRemove);

	}

}
