package service;

import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ejb.GaleriaEJBLocal;
import models.Foto;
import models.Galeria;
import models.Usuario;
import facade.GaleriaEJBFacade;

@Path("/verGaleria")
public class GaleriaService {
	@EJB
	GaleriaEJBLocal galeriaEJB;
	
	@EJB
	GaleriaEJBFacade galeriaFacade;
	
	@POST 
    @Consumes({"application/json"})
	@Produces({"application/json"})
	public Response verGaleria(Galeria galeria){
		List<Foto> fotosGaleria =  galeriaEJB.obtenerGaleria(galeria);
		JsonArrayBuilder jsonArrBuilder = Json.createArrayBuilder();
		for(Foto f : fotosGaleria){
			jsonArrBuilder.add(f.getIdFoto());
		}
        JsonArray jsonObj = jsonArrBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();

	}
	
	@GET
	@Path("/Prueba")	
	@Produces({"application/json"})
	public List<Galeria> prueba(){
		return galeriaFacade.findAll();
	}
}
