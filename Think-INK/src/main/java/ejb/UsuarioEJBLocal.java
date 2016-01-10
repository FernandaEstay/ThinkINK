package ejb;

import javax.ejb.Local;

import models.Usuario;

@Local
public interface UsuarioEJBLocal {
	Usuario Login(Usuario usuario);
	Usuario Registro(Usuario usuario);
	Usuario obtenerUsuario(int id);
	Usuario perfilUsuario(Usuario usuario);
	Boolean comprobarUsuario(Usuario usuario);
}
