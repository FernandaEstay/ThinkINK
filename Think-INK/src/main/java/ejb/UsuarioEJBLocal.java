package ejb;

import javax.ejb.Local;

import models.Usuario;

@Local
public interface UsuarioEJBLocal {
	
	String Login(Usuario usuario);
	String Registro(Usuario usuario);

}
