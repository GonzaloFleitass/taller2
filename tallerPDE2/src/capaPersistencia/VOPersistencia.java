package capaPersistencia;

import capaLogica.minivanes.*;
import capaLogica.paseos.*;

public class VOPersistencia extends Exception {
	private Minivanes minivanesV;
	private Paseos paseosV;

	public VOPersistencia(Minivanes minivanesV, Paseos paseosV) {
		super();
		this.minivanesV = minivanesV;
		this.paseosV = paseosV;
	}

	public Minivanes getMinivanes() {
		return minivanesV;
	}

	public Paseos getPaseos() {
		return paseosV;
	}
}
