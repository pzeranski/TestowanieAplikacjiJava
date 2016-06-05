package com.example.restservicedemo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restservicedemo.domain.TDriver;
import com.example.restservicedemo.service.TDriverManager;

import java.util.List;

@Path("tdriver")
public class TDriverRESTService {	
	
	private TDriverManager tdm = new TDriverManager();

	@GET
	@Path("/{tdriverId}")
	@Produces(MediaType.APPLICATION_JSON)
	public TDriver getTDriver(@PathParam("tdriverId") int id)
	{
		TDriver td = tdm.getTDriver(id);
		return td;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTDriver(TDriver tdriver)
	{
		TDriver tdriverToAdd = new TDriver(tdriver.getFirstName(), tdriver.getStartWork());
		tdm.addTDriver(tdriverToAdd);
		return Response.status(201).entity("TDriver").build();
	}

	@POST
	@Path("/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<TDriver> removeTDriver(TDriver tdriver)
	{
		TDriver tdriverToRemove = tdm.getTDriver(tdriver.getId());
		tdm.removeTDriver(tdriverToRemove.getId());
		return tdm.getAllTDrivers();
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test()
	{
		return "REST API /tdriver is running";
	}

	@GET
	@Path("/all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<TDriver> getAllTDrivers()
	{
		return tdm.getAllTDrivers();
	}

	@DELETE
	public Response clearTDrivers()
	{
		tdm.clearTDrivers();
		return Response.status(200).build();
	}

}