package ejb;

import java.util.List;

import javax.ejb.EJB;

import facade.GaleriaEJBFacade;
import facade.UsuarioEJBFacade;
import models.Galeria;
import models.Usuario;

public class GaleriaEJB implements GaleriaEJBLocal{
	
	@EJB
	UsuarioEJBFacade usuarioFacade;
	
	@EJB
	GaleriaEJBFacade galeriaFacade;
	
	public Galeria obtenerGaleriaUsuario(Usuario usuario, String tipo){
		List<Galeria> galerias = galeriaFacade.findAll();
		int largo = galerias.size();
		for(int i = 0; i < largo; i++){
			if(galerias.get(i).getIdUsuario() == usuario && galerias.get(i).getTipo().equals(tipo)){
				return galerias.get(i);
			}
		}
		return null;
	}
}
