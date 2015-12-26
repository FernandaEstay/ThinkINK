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
		return fotosGaleria;
	}
	
	@GET
	@Path("/Prueba")	
	@Produces({"application/json"})
	public List<Galeria> prueba(){
		return galeriaFacade.findAll();
	}
}
