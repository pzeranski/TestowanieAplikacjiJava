package com.example.junit;
import java.util.List;

public interface IHouseManager {
	
	boolean removeHouse(House house);
	boolean addHouse(House house);	
	List<House> getAll();
	boolean findbyUlica(String ulica);
	boolean findbyNumer(int numer);	
}
