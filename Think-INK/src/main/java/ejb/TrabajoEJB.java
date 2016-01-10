package ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import facade.TrabajoEJBFacade;
import facade.UsuarioEJBFacade;

import java.util.*;

import models.Trabajo;
import models.Usuario;

@Stateless
public class TrabajoEJB implements TrabajoEJBLocal {
	/* @EJB son para utilizar otras funciones de otra clase, sin instanciar*/
	@EJB
	UsuarioEJBFacade usuarioFacade;
  @EJB
  TrabajoEJBFacade trabajoFacade;
	@EJB
	UsuarioEJBLocal usuarioEJB;

  public void guardarTrabajo(Usuario usuario, Trabajo trabajo){
    if(comprobarTrabajo(trabajo) && usuarioEJB.comprobarUsuario(usuario)){ //Entonces el trabajo no existía con anterioridad
      trabajoFacade.create(trabajo);
      usuario.setIdTrabajo(trabajo);
      usuarioFacade.edit(usuario);
    }
    else{ //El trabajo ya existía
			trabajoFacade.create(trabajo);
			if(usuarioEJB.comprobarUsuario(usuario)){
				usuario.setIdTrabajo(trabajo);
				usuarioFacade.edit(usuario);
			}
    }

  }

	public Boolean comprobarTrabajo(Trabajo trabajo){
		List<Trabajo> trabajos = trabajoFacade.findAll(); //Se obtienen todos los trabajos
		for(Trabajo t:trabajos){
			if(trabajo.getNombreLocal() == t.getNombreLocal()){
				return true;
			}
		}
		return false;
	}
}
