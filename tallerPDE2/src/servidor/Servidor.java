package servidor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import capaLogica.Ifachada;
import capaLogica.fachada;
import capaPersistencia.PersistenciaException;

public class Servidor {

    public static void main(String[] args) throws PersistenciaException {
        try {
            int puerto = 1099;
            
            // Intentar iniciar el registro RMI con el puerto 1099, si el server esta encendido tira error...
            try {
                LocateRegistry.createRegistry(puerto);
                System.out.println("RMI iniciado en el puerto " + puerto);
            } catch (RemoteException e) {
                System.out.println("El puerto " + puerto + " ya está en uso. Verificando...");
            }
            
            // Crear la instancia de la fachada
            Ifachada fach = new fachada();
          
            
            try {
    			fach.recuperar();
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (PersistenciaException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
          
	
            // eso hace que quede publicado
            Naming.rebind("//localhost:" + puerto + "/fachada", fach);
            System.out.println("Fachada publicada exitosamente.");

        } catch (RemoteException | MalformedURLException e) {
            System.err.println("Error en RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

