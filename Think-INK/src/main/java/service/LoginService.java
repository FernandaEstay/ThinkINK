package service;

import java.util.List;

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

import ejb.UsuarioEJBLocal;
import facade.UsuarioEJBFacade;
import models.Usuario;

@Path("/login")
public class LoginService {

	@EJB
	UsuarioEJBLocal usuarioEJB;
	@EJB
	UsuarioEJBFacade userFacade;
	
	@POST //cambiar a GET en caso de prueba
    @Consumes({"application/json"})
	@Produces({"application/json"})
    public Response login(Usuario usuario){
		/*
		Usuario user = new Usuario();
		
		user.setNombreUsuario("fernanda");		//si el nombre de usuario o contraseña no existen envia un false
		user.setPass("contrasena");*/
		
        JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
        jsonObjBuilder.add( "message", usuarioEJB.Login(usuario));  //cambiar por user en caso de prueba
        JsonObject jsonObj = jsonObjBuilder.build();
        return Response.status( Response.Status.OK ).entity( jsonObj ).build();
    }
	
	@GET
	@Path("/Prueba")		//lista todos los usuarios de la base de datos
	@Produces({"application/json"})
	public List<Usuario> prueba(){
		return userFacade.findAll();
	}

}
