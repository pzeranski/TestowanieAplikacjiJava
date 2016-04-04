package com.example.junit;

import java.util.ArrayList;
import java.util.List;


public class HouseManager
{
	public IMyList myList;

	public HouseManager(IMyList myList){
		this.myList = myList;
	}
	
	public boolean addHouse(House house)
	{
		return myList.addHouse(house);
	}

	public boolean removeHouse(House house)
	{
		return myList.removeHouse(house);
	}
	
	public List<House> getAllHouses()
	{
		return myList.getAll();
	}
	
	public House findbyUlica(String ulica)
	{
		return myList.findbyUlica(ulica);
	}
	
	public House findbyNumer(int numer)
	{
		return myList.findbyNumer(numer);
	}
}
