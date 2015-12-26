package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
	public List<Foto> verGaleria(Galeria galeria){
		List<Foto> fotosGaleria =  galeriaEJB.obtenerGaleria(galeria);
		/*if(fotosGaleria.size() == 0){
			JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
	        jsonObjBuilder.add("ERROR", "no hay fotos en esta galeria");
	        JsonObject jsonObj = jsonObjBuilder.build();
	        
	        return Response.status(Response.Status.OK).entity(jsonObj).build();
		}
				return Response.status(Response.Status.OK).entity(fotosGaleria.get(0)).build();
			//return null;*/
		return fotosGaleria;
	}
	
	@GET
	@Path("/Prueba")		//lista todos los usuarios de la base de datos
	@Produces({"application/json"})
	public List<Galeria> prueba(){
		return galeriaFacade.findAll();
	}
}
