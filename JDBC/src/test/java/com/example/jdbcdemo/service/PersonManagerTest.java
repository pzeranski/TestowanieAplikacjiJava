package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Person;

public class PersonManagerTest {
	
	
	PersonManager personManager = new PersonManager();
	
	private final static String NAME_1 = "Zenek";
	private final static int YOB_1 = 1945;
	
	private final static String NAME_2 = "Jan";
	private final static int YOB_2 = 2000;
	
	private final static String NAME_3 = "Jan";
	private final static int YOB_3 = 2010;	
	@Test
	public void checkConnection(){
		assertNotNull(personManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		
		Person person1 = new Person(NAME_1, YOB_1);
		
		personManager.clearPersons();
		assertEquals(1,personManager.addPerson(person1));
		
		List<Person> persons = personManager.getAllPersons();
		Person personRetrieved = persons.get(0);
		
		assertEquals(NAME_1, personRetrieved.getName());
		assertEquals(YOB_1, personRetrieved.getYob());
		
	}
	
	@Test
	public void checkUpdating(){
		
		Person person1 = new Person(NAME_1, YOB_1);
		Person person2 = new Person(NAME_2, YOB_2);
		Person person3 = new Person(NAME_3, YOB_3);
		
		personManager.clearPersons();
		assertEquals(1,personManager.addPerson(person1));
		assertEquals(1,personManager.addPerson(person2));
		assertEquals(1,personManager.addPerson(person3));
		
		int id = personManager.addPersonGetID(person1);
		assertEquals(1, personManager.updatePerson(id, person2));
		
		List<Person> persons = personManager.getAllPersons();
		Person personRetrieved = persons.get(3);
		
		assertEquals(NAME_2, personRetrieved.getName());
		assertEquals(YOB_2, personRetrieved.getYob());
		
	}
	@Test
	public void checkDeletePerson(){
		
		Person person1 = new Person(NAME_1, YOB_1);
		Person person2 = new Person(NAME_2, YOB_2);
		Person person3 = new Person(NAME_3, YOB_3);
		
		personManager.clearPersons();
		assertEquals(1,personManager.addPerson(person1));
		assertEquals(1,personManager.addPerson(person2));
		assertEquals(1,personManager.addPerson(person3));
		assertEquals(1,personManager.addPerson(person3));
		assertEquals(1,personManager.addPerson(person3));
		
		assertEquals(1, personManager.deletePerson(person1));
		
		assertEquals(4, personManager.getAllPersons().size());
		
		List<Person> persons = personManager.getAllPersons();
		Person personRetrieved2 = persons.get(0);
		Person personRetrieved3 = persons.get(1);
		
		assertEquals(NAME_2, personRetrieved2.getName());
		assertEquals(YOB_2, personRetrieved2.getYob());	
		
		assertEquals(NAME_3, personRetrieved3.getName());
		assertEquals(YOB_3, personRetrieved3.getYob());
	}
	@Test
	public void checkDeleting(){
		
		Person person1 = new Person(NAME_1, YOB_1);
		Person person2 = new Person(NAME_2, YOB_2);
		Person person3 = new Person(NAME_3, YOB_3);
		
		personManager.clearPersons();
		assertEquals(1,personManager.addPerson(person1));
		assertEquals(1,personManager.addPerson(person2));
		assertEquals(1,personManager.addPerson(person3));
		
		personManager.clearPersons();
		
		assertEquals(0, personManager.getAllPersons().size());
	}
	
	@Test
	public void checkSelectingByName(){
		
		Person person1 = new Person(NAME_1, YOB_1);
		Person person2 = new Person(NAME_1, YOB_2);
		Person person3 = new Person(NAME_2, YOB_3);
		
		personManager.clearPersons();
		assertEquals(1,personManager.addPerson(person1));
		assertEquals(1,personManager.addPerson(person2));
		assertEquals(1,personManager.addPerson(person3));
		
		
		assertEquals(2, personManager.getPersonsByName(NAME_1).size());
	}	
}
