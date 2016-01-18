package service;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import ejb.UsuarioEJBLocal;
import facade.UsuarioEJBFacade;
import models.Usuario;

import ejb.TrabajoEJBLocal;
import facade.TrabajoEJBFacade;
import models.Trabajo;

import java.util.*;
@Path("/trabajo")
public class TrabajoService {

	@EJB
	UsuarioEJBLocal usuarioEJB;
	@EJB
	UsuarioEJBFacade usuarioFacade;
  @EJB
  TrabajoEJBFacade trabajoFacade;
  @EJB
  TrabajoEJBLocal trabajoEJB;

  @POST
  @Path("/crearTrabajo")
	@Consumes({"application/json"})
  @Produces({"application/json"})
  public Response crearTrabajo(JsonObject request){
		JsonArrayBuilder array = Json.createArrayBuilder();

					int id = request.getInt("idUsuario");
					String nombre = request.getString("nombreLocal");
					Double longitud = Double.parseDouble(request.getString("longitud"));
					Double latitud = Double.parseDouble(request.getString("latitud"));

					Usuario u= usuarioEJB.obtenerUsuario(id);
					Trabajo t = new Trabajo();
					t.setNombreLocal(nombre);
					t.setLongitud(longitud);
					t.setLatitud(latitud);
          u.setIdTrabajo(t);
          usuarioFacade.edit(u);
          if(!trabajoEJB.comprobarTrabajo(t)) trabajoFacade.create(t); //Si no existe el trabajo, entonces se guarda
          			
			        return Response.status(201).entity(request).build();
  				}
	@POST
	@Path("/obtenerTrabajo")
	@Produces({"application/json"})
	public Trabajo prueba(Usuario usuario){
		Usuario u = usuarioEJB.obtenerUsuario(usuario.getIdUsuario());
		return u.getIdTrabajo();
	}
	@GET
	@Path("/Prueba")
	@Produces({"application/json"})
	public List<Trabajo> prueba(){
		return trabajoFacade.findAll();
	}
	}
