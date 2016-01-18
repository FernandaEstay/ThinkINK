package ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import facade.FotoEJBFacade;
import facade.MeGustaEJBFacade;
import facade.UsuarioEJBFacade;
import models.Foto;
import models.MeGusta;
import models.Usuario;

@Stateless
public class MeGustaEJB implements MeGustaEJBLocal{
	
	@EJB
	UsuarioEJBFacade usuarioFacade;

	@EJB
	FotoEJBFacade fotoFacade;
	
	@EJB
	MeGustaEJBFacade megustaFacade;
	
	public boolean guardarMegusta(int idUsuario, int idFoto){
		if(comprobarUsuario(idUsuario) && comprobarFoto(idFoto)){
			Foto foto = fotoFacade.find(idFoto);
			Usuario user = usuarioFacade.find(idUsuario);
			MeGusta meGusta = new MeGusta();
			meGusta.setIdFoto(foto);
			meGusta.setIdUsuario(user);
			
			megustaFacade.create(meGusta);
			
			int suma = foto.getCantMeGusta() + 1;
			foto.setCantMeGusta(suma);
			
			fotoFacade.edit(foto);
			
			return true;
			
		}
		return false;
	}
	
	public boolean existeMegusta(int idUsuario, int idFoto){
		
		for(MeGusta m:megustaFacade.findAll()){
			if(m.getIdFoto().getIdFoto() == idFoto && m.getIdUsuario().getIdUsuario() == idUsuario){
				return false;
			}
		}
		return true;
	}
	/**
	 * Función que comprueba la existencia de un usuario a través de la ID
	 * @param id
	 * @return true en caso de que el usuario exista o false si no existe
	 */
	public boolean comprobarUsuario(int id){
		List <Usuario> usuarios = usuarioFacade.findAll();
		for(Usuario u : usuarios){
			if(u.getIdUsuario() == id){
				return true;
			}
		}
		return false;
	}
	
	public boolean comprobarFoto(int id){
		List <Foto> fotos = fotoFacade.findAll();
		for(Foto f : fotos){
			if(f.getIdFoto() == id){
				return true;
			}
		}
		return false;
	}
}
