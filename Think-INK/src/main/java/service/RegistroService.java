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

@Path("/registro")
public class RegistroService {
	
	@EJB
	UsuarioEJBLocal usuarioEJB;
	
	@EJB
	UsuarioEJBFacade usuarioFacade;
	
	@POST 
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response registro(Usuario usuario){

		Usuario user = new Usuario();
		
		user = usuarioEJB.Registro(usuario);
		
		if(user.getCorreo() == null){
			JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
	        jsonObjBuilder.add("ERROR", "El nombre de usuario o correo no se encuentran disponibles");
	        JsonObject jsonObj = jsonObjBuilder.build();
			return Response.status(Response.Status.OK).entity(jsonObj).build();
		}
		else{
			return Response.status(Response.Status.OK).entity(user).build();
		}	
	}
}