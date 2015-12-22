package ejb;


import javax.ejb.EJB;

import facade.FotoEJBFacade;
import facade.UsuarioEJBFacade;
import models.Foto;

public class FotoEJB implements FotoEJBLocal {
		
	@EJB
	FotoEJBFacade fotoFacade;
	
	@EJB
	UsuarioEJBFacade usuarioFacade;
	
	@Override
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
	
}
