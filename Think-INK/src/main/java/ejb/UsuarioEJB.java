package ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import facade.UsuarioEJBFacade;

import java.util.*;

import models.Usuario;

@Stateless
public class UsuarioEJB implements UsuarioEJBLocal {
	/* @EJB son para utilizar otras funciones de otra clase, sin instanciar*/
	@EJB 
	UsuarioEJBFacade usuarioFacade;
	
	@Override
	public String Login(Usuario usuario){
		 List <Usuario> list = usuarioFacade.findAll();
         int largo = list.size();
         int contador = 0;
         while(largo != 0){
             if(usuario.getNombreUsuario().equals(list.get(contador).getNombreUsuario()) && usuario.getPass().equals(list.get(contador).getPass())){
                 return "true";
             }
             contador ++;
             largo --;
         }
         return "false";   
	}
	
	@Override
	public String Registro(Usuario usuario){
		if(VerificarCorreo(usuario) && VerificarNombreUsuario(usuario) ){
			usuarioFacade.create(usuario);
			return "True";
		}
		return "False";
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