package capaPersistencia;

import capaLogica.destinos.Destinos;
import capaLogica.minivanes.*;
import capaLogica.paseos.*;

public class VOPersistencia extends Exception {
	private Minivanes minivanesV;
	private Paseos paseosV;
	private Destinos desV;

	public VOPersistencia(Minivanes minivanesV, Paseos paseosV, Destinos desV) {
		super();
		this.minivanesV = minivanesV;
		this.paseosV = paseosV;
		this.desV = desV;
	}

	public Minivanes getMinivanes() {
		return minivanesV;
	}

	public Paseos getPaseos() {
		return paseosV;
	}
	
	public Destinos getDestinos() {
		return desV;
	}
}
