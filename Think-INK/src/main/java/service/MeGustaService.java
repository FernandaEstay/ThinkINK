package service;

import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ejb.MeGustaEJB;
import ejb.MeGustaEJBLocal;
import facade.MeGustaEJBFacade;
import models.MeGusta;


@Path("/megusta")
public class MeGustaService {
	
	@EJB
	MeGustaEJBFacade meGustaFacade;
	
	@EJB
	MeGustaEJBLocal meGustaEJB;
	
	@POST
	@Path("/guardar")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response guardarMegusta(JsonObject request){

		int idUsuario = request.getInt("idUsuario");
		int idFoto = request.getInt("idFoto");
			
	    if(meGustaEJB.guardarMegusta(idUsuario, idFoto)){
	    	JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
	        jsonObjBuilder.add("Ok", "Ok");
	        JsonObject jsonObj = jsonObjBuilder.build();
		    return Response.status(201).entity(jsonObj).build();
	  	}
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add("ERROR", "No a sido posible llevar a cabo su solicitud");
        JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
	}
	
	@GET
	@Path("/Prueba")		//lista todos los usuarios de la base de datos
	@Produces({"application/json"})
	public List<MeGusta> prueba(){
		MeGusta like = new MeGusta();
		List<MeGusta> me = meGustaFacade.findAll();
		me.add(like);
		return me;
	}
}
