package com.example.restservicedemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Train {
	
	private int id;
	private String number;	
	private int yop;
	private TDriver tdriver;
	
	public Train()
	{
	}	
	public Train(String number, int yop)
	{
		this.number = number;
		this.yop = yop;
	}
	public Train(String number, int yop, TDriver tdriver)
	{
		this.number = number;
		this.yop = yop;
		this.tdriver = tdriver;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getNumber()
	{
		return number;
	}
	public void setNumber(String number)
	{
		this.number = number;
	}
	public int getYop()
	{
		return yop;
	}
	public void setYop(int yop)
	{
		this.yop = yop;
	}
	public TDriver getDriver()
	{
		return tdriver;
	}
	public void setDriver(TDriver tdriver)
	{
		this.tdriver = tdriver;
	}
	public boolean hasId()
	{
		return id > 0;
	}
}