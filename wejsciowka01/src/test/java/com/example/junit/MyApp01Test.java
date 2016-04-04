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
	public void addingCheck()
	{
		House domek = new House("Ulica", true, 1);
		List<House> houses = new ArrayList<House>();
		houses.add(domek);
		expect(mock.addHouse(domek)).andReturn(true).atLeastOnce();
		replay(mock);
		assertEquals(true, myApp.addHouse(domek));
		verify(mock);
	}
	@Test
	public void removingCheck()
	{
		House domek = new House("Ulica", true, 1);
		List<House> houses = new ArrayList<House>();
		houses.add(domek);
		expect(mock.removeHouse(domek)).andReturn(true).atLeastOnce();
		replay(mock);
		assertEquals(true, myApp.removeHouse(domek));
		verify(mock);
	}	
	@Test
	public void getAllCheck()
	{
		House domek = new House("Ulica", true, 1);
		List<House> houses = new ArrayList<House>();
		houses.add(domek);
		expect(mock.getAll()).andReturn(houses).atLeastOnce();
		replay(mock);
		assertEquals("Ulica", myApp.getAll().get(0).getUlica());
		verify(mock);
	}		
	
	@Test
	public void findByXCheck() {
		
		House domek = new House("Ulica", true, 1);
		House domek2 = new House("Ulica2", true, 2);
		List<House> houses = new ArrayList<House>();
		houses.add(domek);
		houses.add(domek2);
		
		expect(mock.findbyUlica("Ulica")).andReturn(true).atLeastOnce();

		expect(mock.findbyNumer(1)).andReturn(true).atLeastOnce();
		
		replay(mock);
		
		assertEquals(true, myApp.findbyUlica("Ulica"));
		assertEquals(true, myApp.findbyNumer(1));
		verify(mock);
	}
}
