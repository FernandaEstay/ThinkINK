package ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import facade.GaleriaEJBFacade;
import facade.UsuarioEJBFacade;

import java.util.*;

import models.Galeria;
import models.Usuario;

@Stateless
public class UsuarioEJB implements UsuarioEJBLocal {
	/* @EJB son para utilizar otras funciones de otra clase, sin instanciar*/
	@EJB 
	UsuarioEJBFacade usuarioFacade;
	
	@EJB
	GaleriaEJBFacade galeriaFacade;
	
	@Override
	public Usuario Login(Usuario usuario){
		 List <Usuario> list = usuarioFacade.findAll();
		 Usuario usuarioLogin = new Usuario();
		 
         int largo = list.size();
         int contador = 0;
         while(largo != 0){
             if(usuario.getNombreUsuario().equals(list.get(contador).getNombreUsuario()) && usuario.getPass().equals(list.get(contador).getPass())){
                 
            	 usuarioLogin.setCorreo(list.get(contador).getCorreo());
                 usuarioLogin.setIdUsuario(list.get(contador).getIdUsuario());
                 usuarioLogin.setNombreUsuario(list.get(contador).getNombreUsuario());
            	 return usuarioLogin;
             }
             contador ++;
             largo --;
         }
         return usuarioLogin;   
	}
	
	@Override
	public Usuario Registro(Usuario usuario){
		
		Usuario usuarioRegistro = new Usuario();
		
		if(VerificarCorreo(usuario) && VerificarNombreUsuario(usuario) ){
			
			Date fechaCreacion = new Date();
			usuario.setFechaCreacion(fechaCreacion);
			usuario.setEstadoCuenta("ACTIVA");
			
			usuarioFacade.create(usuario);
			List <Usuario> usuarios = usuarioFacade.findAll();
			int largo = usuarios.size();
			
			Galeria galeriaSubida = new Galeria();
			Galeria galeriaEtiqueta = new Galeria();
			
			for(int i = 0; i < largo; i++){
				if(usuario.getNombreUsuario().equals(usuarios.get(i).getNombreUsuario())){
					
					usuarioRegistro.setCorreo(usuarios.get(i).getCorreo());
					usuarioRegistro.setIdUsuario(usuarios.get(i).getIdUsuario());
					usuarioRegistro.setNombreUsuario(usuarios.get(i).getNombreUsuario());
					
					if(usuarios.get(i).getTipoUsuario().equals("TATUADOR")){
					
						galeriaSubida.setIdUsuario(usuarios.get(i));
						galeriaSubida.setNombre("SUBIDAS");
						galeriaSubida.setTipo("SUBIDA");
						galeriaFacade.create(galeriaSubida);
						
						galeriaEtiqueta.setIdUsuario(usuarios.get(i));
						galeriaEtiqueta.setNombre("ETIQUETA");
						galeriaEtiqueta.setTipo("ETIQUETA");
						galeriaFacade.create(galeriaEtiqueta);
					}
					if(usuarios.get(i).getTipoUsuario().equals("NORMAL")){
						galeriaSubida.setIdUsuario(usuarios.get(i));
						galeriaSubida.setNombre("SUBIDAS");
						galeriaSubida.setTipo("SUBIDA");
						galeriaFacade.create(galeriaSubida);;
					}
					
				}
			}
			return usuarioRegistro;
		}
		return usuarioRegistro;
	}
	
	public Boolean VerificarCorreo(Usuario usuario){
		List <Usuario> usuarios = usuarioFacade.findAll();
		int largo = usuarios.size();
		for(int i = 0; i < largo; i++){
			if(usuario.getCorreo().equals(usuarios.get(i).getCorreo())){
				return false;
			}
		}
		return true;
	}
	
	public Boolean VerificarNombreUsuario(Usuario usuario){
		List <Usuario> usuarios = usuarioFacade.findAll();
		int largo = usuarios.size();
		for(int i = 0; i < largo; i++){
			if(usuario.getNombreUsuario().equals(usuarios.get(i).getNombreUsuario())){
					return false;
			}
		}
		return true;
	}
}