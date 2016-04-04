package com.example.junit;

import java.util.ArrayList;
import java.util.List;


public class HouseManager {

	List<House> houses = new ArrayList<House>();


	public void addHouse(House house){

		houses.add(house);

	}

	public void removeHouse(House house){

		houses.remove(house);

	}
	public List<House> getAll(){
		return houses;
	}
	public boolean findbyUlica(String ulica)
	{
		for(House house: houses)
		{
			if(house.getUlica().equals(ulica))
			{
				return true;
			}
		}
		return false;
	}
	public boolean findbyNumer(int numer)
	{
		for(House house: houses)
		{
			if(house.getNumer() == numer)
			{
				return true;
			}
		}
		return false;
	}
}
