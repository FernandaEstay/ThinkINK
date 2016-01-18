package ejb;

import javax.ejb.Local;

@Local
public interface MeGustaEJBLocal {
	public boolean guardarMegusta(int idUsuario, int idFoto);
	public boolean existeMegusta(int idUsuario, int idFoto);
}
