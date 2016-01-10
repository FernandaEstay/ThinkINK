package service;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ejb.GaleriaEJBLocal;
import models.Foto;
import models.Galeria;
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
		Collections.reverse(fotosGaleria);
		JsonArrayBuilder jsonArrBuilder = Json.createArrayBuilder();
		
		for(Foto f : fotosGaleria){
			
			jsonArrBuilder.add(Json.createObjectBuilder().add("idFoto", f.getIdFoto())
					.add("fecha", f.getFechaSubida().toString())
					.add("idUsuario", f.getIdUsuario().getIdUsuario())
					.add("nombre", f.getIdUsuario().getNombreUsuario())
					);
		}
		
        JsonArray jsonObj = jsonArrBuilder.build();
		
        return Response.status(Response.Status.OK).entity(jsonObj).build();

	}
	
	@GET
	@Path("/Prueba")	
	@Produces({"application/json"})
	public Response prueba(){		
		JsonArrayBuilder jsonArrBuilder = Json.createArrayBuilder();

		for(Foto f : galeriaFacade.findAll().get(0).getFotoCollection()){
					
			jsonArrBuilder.add(Json.createObjectBuilder().add("idFoto", f.getIdFoto())
					.add("fecha", f.getFechaSubida().toString())
					.add("idUsuario", f.getIdUsuario().getIdUsuario()));
		}
		
        JsonArray jsonObj = jsonArrBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
		//return galeriaFacade.findAll();
	}
}
