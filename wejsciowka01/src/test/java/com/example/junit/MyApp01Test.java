package com.example.junit;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MyApp01Test {

	// SUT
	private MyApp myApp;
	private IHouseManager mock;

	@Before
	public void setUp() {
		mock = createMock(IHouseManager.class);
		myApp = new MyApp(mock);
	}

	@Test
	public void addingCheck() {
		
		House domek = new House("Ulica", true, 1);
		List<House> houses = new ArrayList<House>();
		houses.add(domek);
		
		
		expect(mock.addHouse(domek)).andReturn(true).atLeastOnce();
				
		expect(mock.removeHouse(domek)).andReturn(true).atLeastOnce();
		
		expect(mock.getAll()).andReturn(houses).atLeastOnce();
		
		expect(mock.findbyUlica("Ulica")).andReturn(true).atLeastOnce();

		expect(mock.findbyNumer(1)).andReturn(true).atLeastOnce();
		
		replay(mock);
		
		assertEquals(true, myApp.addHouse(domek));
		assertEquals(true, myApp.findbyUlica("Ulica"));
		assertEquals("Ulica", myApp.getAll().get(0).getUlica());
		assertEquals(true, myApp.findbyNumer(1));
		assertEquals(true, myApp.removeHouse(domek));
		verify(mock);
	}
}
