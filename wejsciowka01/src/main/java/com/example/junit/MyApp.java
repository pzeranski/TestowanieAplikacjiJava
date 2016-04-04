package com.example.junit;
import java.util.List;
public class MyApp {
	
	private IHouseManager housm;

	public MyApp(IHouseManager housm) {
		this.housm = housm;
	}
	
	public boolean addHouse(House house){
		return housm.addHouse(house);	
	}
	
	public boolean removeHouse(House house){
		return housm.removeHouse(house);
	}
	public List<House> getAll()
	{
		return housm.getAll();
	}
	public boolean findbyUlica(String ulica)
	{
		return housm.findbyUlica(ulica);
	}
	public boolean findbyNumer(int numer)
	{
		return housm.findbyNumer(numer);
	}
}
