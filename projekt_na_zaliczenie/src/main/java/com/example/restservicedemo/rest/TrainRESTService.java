package com.example.restservicedemo.rest;

import com.example.restservicedemo.domain.Train;
import com.example.restservicedemo.service.TrainManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("train")
public class TrainRESTService {

    private TrainManager td = new TrainManager();

    @GET
    @Path("/{trainId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Train getTrain(@PathParam("trainId") int id)
    {
        Train t = td.getTrain(id);
        return t;
    }

    @GET
    @Path("/all")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Train> getAllTrains()
    {
        return td.getAllTrains();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewTrain(Train train)
    {
        Train trainToAdd = new Train(train.getNumber(), train.getYop());
        td.addTrain(trainToAdd);
        return Response.status(201).entity("success").build();
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeTrain(Train train)
    {
        Train trainToRemove = td.getTrain(train.getId());
        td.removeTrain(trainToRemove);
        return Response.status(201).entity("success").build();
    }
    
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test()
	{
		return "REST API /train is running";
	}
	
    @DELETE
    public Response clearTrains()
    {
        td.clearTrains();
        return Response.status(200).build();
    }
}