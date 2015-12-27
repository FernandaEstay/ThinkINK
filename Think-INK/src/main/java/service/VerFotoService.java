package service;

import java.io.File;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import ejb.FotoEJBLocal;
import ejb.UsuarioEJBLocal;
import facade.FotoEJBFacade;
import facade.UsuarioEJBFacade;
import models.Foto;
import models.Galeria;
import models.Usuario;

@Path("/verFoto")
public class VerFotoService {

	@EJB
	FotoEJBLocal fotoEJB;
	@EJB
	FotoEJBFacade fotoFacade;
	@EJB
	UsuarioEJBLocal usuarioEJB;
	@EJB
	UsuarioEJBFacade userFacade;
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

	@GET
	@Path("/{idfoto}")
	@Produces("image/png")
	public Response getFile(@PathParam("idfoto") String idfoto) {
		for(Foto f:fotoFacade.findAll()){
			if(f.getIdFoto() == Integer.parseInt(idfoto)){
				File file = new File(f.getImagen());
				ResponseBuilder response = Response.ok((Object) file);
				return response.build();
			}
		}
		ResponseBuilder response = Response.status(Status.FORBIDDEN);
		return response.build();

	}

	@GET
	@Path("/resized/{idfoto}")
	@Produces("image/png")
	public Response getFileResized(@PathParam("idfoto") String idfoto) {
		for(Foto f:fotoFacade.findAll()){
			if(f.getIdFoto() == Integer.parseInt(idfoto)){
				File file = new File(f.getImagenResized());
				ResponseBuilder response = Response.ok((Object) file);
				return response.build();
			}
		}
		ResponseBuilder response = Response.status(Status.FORBIDDEN);
		return response.build();

	}

}
