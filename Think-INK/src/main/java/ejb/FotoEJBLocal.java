package ejb;
import javax.ejb.Local;

import models.Foto;

@Local
public interface FotoEJBLocal {
	String subirFoto(Foto foto);
	Foto verFoto(Foto foto);
}
