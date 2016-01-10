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
	
	public Usuario Login(Usuario usuario){
		 List <Usuario> list = usuarioFacade.findAll();
		 Usuario usuarioLogin = new Usuario();
		 
        int largo = list.size();
        int contador = 0;
        while(largo != 0){
            if(usuario.getCorreo().equals(list.get(contador).getCorreo()) && usuario.getPass().equals(list.get(contador).getPass())){
                
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
	
	public Usuario Registro(Usuario usuario){
		
		Usuario usuarioRegistro = new Usuario();
		
		if(VerificarCorreo(usuario) && VerificarNombreUsuario(usuario) ){
			crearUsuario(usuario);
			List <Usuario> usuarios = usuarioFacade.findAll();
			int largo = usuarios.size();
			
			for(int i = 0; i < largo; i++){
				
				if(usuario.getNombreUsuario().equals(usuarios.get(i).getNombreUsuario())){
					usuarioRegistro.setCorreo(usuarios.get(i).getCorreo());
					usuarioRegistro.setIdUsuario(usuarios.get(i).getIdUsuario());
					usuarioRegistro.setNombreUsuario(usuarios.get(i).getNombreUsuario());
					crearGaleria(usuarios.get(i), "SUBIDA");
					
					if(usuarios.get(i).getTipoUsuario().equals("TATUADOR")){						
						crearGaleria(usuarios.get(i),"ETIQUETA");
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
	
	public void crearUsuario(Usuario usuario){
		Date fechaCreacion = new Date();
		usuario.setFechaCreacion(fechaCreacion);
		usuario.setEstadoCuenta("ACTIVA");
		
		usuarioFacade.create(usuario);
	}
	
	public void crearGaleria(Usuario usuario, String nombre){
		Galeria galeria = new Galeria();
		galeria.setIdUsuario(usuario);
		galeria.setNombre(nombre);
		galeria.setTipo(nombre);
		galeriaFacade.create(galeria);
	}
	
	public Usuario obtenerUsuario(int id){
		Usuario usuario = new Usuario();
		List <Usuario> usuarios = usuarioFacade.findAll();
		int largo = usuarios.size();
		for(int i = 0; i < largo; i++){
			if(usuarios.get(i).getIdUsuario() == id){
				usuario = usuarios.get(i);
				return usuario;
			}
		}
		return usuario;
	}
	
	public Usuario perfilUsuario(Usuario usuario){
		Usuario user = new Usuario();
		List <Usuario> usuarios = usuarioFacade.findAll();
		int largo = usuarios.size();
		for(int i = 0; i < largo; i++){
			if(usuarios.get(i).getIdUsuario() == usuario.getIdUsuario()){
				user.setAvatar(usuarios.get(i).getAvatar());
				user.setCorreo(usuarios.get(i).getCorreo());
				user.setDescripcion(usuarios.get(i).getDescripcion());
				user.setFechaCreacion(usuarios.get(i).getFechaCreacion());
				user.setIdUsuario(usuarios.get(i).getIdUsuario());
				user.setNombreUsuario(usuarios.get(i).getNombreUsuario());
				user.setPortada(usuarios.get(i).getPortada());
				user.setTipoUsuario(usuarios.get(i).getTipoUsuario());
			}
		}
		return user;
	}

	public Boolean comprobarUsuario(Usuario usuario){
		List<Usuario> usuarios = usuarioFacade.findAll();
		for(Usuario u:usuarios){
			if(u.getIdUsuario() == usuario.getIdUsuario()){
				return true;
			}
		}
		return false;
	}

}