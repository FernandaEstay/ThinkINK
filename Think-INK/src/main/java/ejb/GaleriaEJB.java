package ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import facade.GaleriaEJBFacade;
import facade.UsuarioEJBFacade;
import models.Foto;
import models.Galeria;
import models.Usuario;
import facade.FotoEJBFacade;

@Stateless
public class GaleriaEJB implements GaleriaEJBLocal{
	
	@EJB
	UsuarioEJBFacade usuarioFacade;
	
	@EJB
	GaleriaEJBFacade galeriaFacade;
	
	@EJB
	FotoEJBFacade fotoFacade;
	
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
	
	public List<Foto> obtenerGaleria(Galeria galeria){
		List<Galeria> galerias = galeriaFacade.findAll();
		//List<Foto> fotos = fotoFacade.findAll();
		List<Foto> fotosEncontradas = new ArrayList<Foto>();
		
		for(Galeria g : galerias){
			if(g.getTipo().equals(galeria.getTipo()) && g.getIdUsuario().getIdUsuario() == galeria.getIdUsuario().getIdUsuario()){
				for(Foto f:g.getFotoCollection()){
					fotosEncontradas.add(f);
				}
				
				//fotosEncontradas.addAll(g.getFotoCollection());
				return fotosEncontradas;
			}
		}
			/*
			for(Foto f : fotos){
				//if(g.getIdUsuario().getIdUsuario() == galeria.getIdUsuario().getIdUsuario() && g.getTipo().equals(galeria.getTipo()) && f.getIdGaleria() == g){
				if(f.getIdGaleria() == g){
					
					Foto fotoNueva = new Foto();
					fotoNueva.setIdFoto(f.getIdFoto());
					fotoNueva.setCantMeGusta(f.getCantMeGusta());
					fotoNueva.setFechaSubida(f.getFechaSubida());
					fotoNueva.setIdGaleria(f.getIdGaleria());
					fotoNueva.setImagen(f.getImagen());
					
					Galeria galeriaNueva = new Galeria(); 
					galeriaNueva.setIdGaleria(g.getIdGaleria());
					galeriaNueva.setTipo(g.getTipo());
					
					fotoNueva.setIdGaleria(galeriaNueva);
					
					Usuario usuarioNuevo = new Usuario();
					usuarioNuevo.setIdUsuario(g.getIdUsuario().getIdUsuario());
					usuarioNuevo.setNombreUsuario(g.getIdUsuario().getNombreUsuario());
					
					fotoNueva.setIdUsuario(usuarioNuevo);
					
					fotosEncontradas.add(fotoNueva);
				}
			}
		}*/
		return fotosEncontradas;
	}
}
