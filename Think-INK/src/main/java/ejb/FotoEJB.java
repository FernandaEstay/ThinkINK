package ejb;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import facade.FotoEJBFacade;
import facade.UsuarioEJBFacade;
import models.Foto;
import models.Galeria;
import models.Usuario;

@Stateless
public class FotoEJB implements FotoEJBLocal {
		
	@EJB
	FotoEJBFacade fotoFacade;
	
	@EJB
	UsuarioEJBFacade usuarioFacade;
	
	public String subirFoto(Foto foto){
		if(comprobarID(foto)){
			fotoFacade.create(foto);
			return "true";
		}
		return "false";
	}
	
	public boolean comprobarID(Foto foto){
		if(foto.getIdUsuario() == null){
				return false;
		}
		return true;
	}
	
	public Foto verFoto(Foto foto){
		List<Foto> fotos = fotoFacade.findAll();
		Foto fotoFind = new Foto();
		for(Foto f : fotos){
			if(f.getIdFoto() == foto.getIdFoto()){
				fotoFind.setIdFoto(f.getIdFoto());
				fotoFind.setCantMeGusta(f.getCantMeGusta());
				fotoFind.setFechaSubida(f.getFechaSubida());
				fotoFind.setIdGaleria(f.getIdGaleria());
				fotoFind.setImagen(f.getImagen());
				
				Galeria galeriaNueva = new Galeria(); 
				galeriaNueva.setIdGaleria(f.getIdGaleria().getIdGaleria());
				galeriaNueva.setTipo(f.getIdGaleria().getTipo());
				
				fotoFind.setIdGaleria(galeriaNueva);
				
				Usuario usuarioNuevo = new Usuario();
				usuarioNuevo.setIdUsuario(f.getIdUsuario().getIdUsuario());
				usuarioNuevo.setNombreUsuario(f.getIdUsuario().getNombreUsuario());
				
				fotoFind.setIdUsuario(usuarioNuevo);
			}
		}
		return fotoFind;
	}
	
}
