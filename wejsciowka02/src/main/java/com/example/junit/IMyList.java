package com.example.junit;
import java.util.List;

public interface IMyList
{
	boolean removeHouse(House house);
	boolean addHouse(House house);	
	public List<House> getAll();
	public House findbyUlica(String ulica);
	public House findbyNumer(int numer);	
}
