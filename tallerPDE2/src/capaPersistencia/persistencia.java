package capaPersistencia;

import java.io.*;
import capaPersistencia.VOPersistencia;

public class persistencia {

	public void respaldar(String nomArch, VOPersistencia voPersistencia) throws PersistenciaException {
		try {
			// Abro el archivo y creo un flujo de comunicación hacia él
			FileOutputStream f = new FileOutputStream(nomArch);
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Escribo el objeto en el archivo a través del flujo
			o.writeObject(voPersistencia);
			o.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error al respaldar el archivo: " + e.getMessage());
		}
	}

	public VOPersistencia recuperar(String nomArch) throws PersistenciaException {
		try {
			// Abro el archivo y creo un flujo de comunicación hacia él
			FileInputStream f = new FileInputStream(nomArch);
			ObjectInputStream o = new ObjectInputStream(f);

			// Leo el objeto desde el archivo a través del flujo
			VOPersistencia voPersistencia = (VOPersistencia) o.readObject();
			o.close();
			f.close();
			return voPersistencia;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenciaException("Error al recuperar el archivo: " + e.getMessage());
		}
	}
}
