package service;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ejb.FotoEJBLocal;
import models.Foto;

@Path("/verFoto")
public class VerFotoService {
	
	@EJB
	FotoEJBLocal fotoEJB;
	
	@POST 
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response verFoto(Foto foto){
		Foto f = fotoEJB.verFoto(foto);
		if(f.getImagen() == null){
			JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
	        jsonObjBuilder.add("ERROR", "No se encuentra la fotografia");
	        JsonObject jsonObj = jsonObjBuilder.build();
			return Response.status(Response.Status.OK).entity(jsonObj).build();
		}
		return Response.status(Response.Status.OK).entity(f).build();
		
	}
	
}
