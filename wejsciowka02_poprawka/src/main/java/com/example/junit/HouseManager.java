package com.example.junit;

import java.util.ArrayList;
import java.util.List;


public class HouseManager
{
	private ArrayList<House> myList = new ArrayList<House>();
	
	public boolean addHouse(House house)
	{
		return myList.add(house);
	}

	public boolean removeHouse(House house)
	{
		return myList.remove(house);
	}
	
	public List<House> getAllHouses()
	{
		return myList;
	}
	public House getHouse(int nr)
	{
		return myList.get(nr);
	}
	
	public House getHouse(House house)
	{
		int index = myList.indexOf(house);
		return myList.get(index);
	}
}
