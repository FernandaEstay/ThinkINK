package service;

import java.util.List;
import java.util.Date;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ejb.UsuarioEJBLocal;
import facade.UsuarioEJBFacade;
import models.Usuario;

@Path("/registro")
public class RegistroService {
	
	@EJB
	UsuarioEJBLocal usuarioEJB;
	
	@EJB
	UsuarioEJBFacade usuarioFacade;
	
	@POST //cambiar a GET en caso de prueba
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Response registro(Usuario usuario){
		/*
		Usuario user = new Usuario();
		
		user.setNombreUsuario("fernanda");
		user.setCorreo("festay14@gmail.com");
		user.setEstadoCuenta("activa");
		user.setPass("contrasena");
		user.setTipoUsuario("tatuador");
		Date fechaCreacion = new Date();
		user.setFechaCreacion(fechaCreacion);*/
		
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		jsonObjBuilder.add("mensaje", usuarioEJB.Registro(usuario)); //cambiar usuario por user en caso de prueba
		JsonObject jsonObj = jsonObjBuilder.build();
		return Response.status(Response.Status.OK).entity(jsonObj).build();
	
	}
}