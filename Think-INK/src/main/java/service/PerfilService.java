package service;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.core.Response;

import ejb.UsuarioEJBLocal;
import facade.UsuarioEJBFacade;
import models.Usuario;

@Path("/verPerfil")
public class PerfilService {

	@EJB
	UsuarioEJBLocal usuarioEJB;
	
	@EJB
	UsuarioEJBFacade usuarioFacade;
	
	@POST 
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response perfil(Usuario usuario){
		Usuario user = new Usuario(); 
		user = usuarioEJB.perfilUsuario(usuario);
		
		if(user.getCorreo() == null){
			JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
	        jsonObjBuilder.add("ERROR", "No se encuentra el usuario");
	        JsonObject jsonObj = jsonObjBuilder.build();
			return Response.status(Response.Status.OK).entity(jsonObj).build();
		}
		else{
			return Response.status(Response.Status.OK).entity(user).build();
		}
		
	}
}