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

public class HouseManagerSteps {
	
	private HouseManager hous;
	private IMyList mock;
	
	List<House> houses = new ArrayList<House>();
	
	@Given("a housemanager")
	public void housemanagerSetup(){
		hous = new HouseManager(mock);
	}
	
	@When("add $dom house")
	public void setArguments(int dom){
		House domek = new House("Ulica", true, 1);
		for(int i=0; i<dom; i++)
		{
			houses.add(domek);
		}
	}
    
    @Then("getCount should return $result")
  	public void shouldgetCount(int result){
  		assertEquals(result, hous.getCount());
  	}
}
