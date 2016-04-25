package com.example.junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class HouseManagerSteps
{
	private HouseManager manager;
	private House house;
	
	@Given("a housemanager")
	public void housemanagerSetup(){
		manager = new HouseManager();
	}
	
	@When("add house with street named $street and number $number to manager")
	public void setArguments(String street, int number){
		this.house = new House(street, true, number);
		manager.addHouse(this.house);
	}
    
    @Then("size of houses should return $result")
  	public void shouldgetCount(int result){
  		assertEquals(result, manager.getAllHouses().size());
  	}
	
	@When("delete house with street named $street and number $number")
	public void deleteHouse(String street, int number) {
		manager.removeHouse(house);
		assertEquals(street, house.getUlica());
		assertEquals(number, house.getNumer());
	}
	
	@Then("Then size of houses should return $size")
	public void checkSizeAfterDelete(String name, int size) {
		assertEquals(size, manager.getAllHouses().size());
	}
}
