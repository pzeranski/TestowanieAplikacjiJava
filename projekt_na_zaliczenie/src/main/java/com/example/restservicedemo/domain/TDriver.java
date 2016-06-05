package com.example.restservicedemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TDriver {
	
	private int id;
	private String firstName;
	private int startWork;
	

	public TDriver()
	{
	}
	public TDriver(String firstName, int startWork)
	{
		this.firstName = firstName;
		this.startWork = startWork;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public int getStartWork()
	{
		return startWork;
	}
	public void setStartWork(int startWork)
	{
		this.startWork = startWork;
	}
	public boolean hasId()
	{
		return id > 0;
	}
}
