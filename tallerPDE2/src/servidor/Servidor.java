package servidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import capaLogica.fachada;

public class Servidor {
	
	public static void main (String [] args)
	{
	try
	{ // pongo a correr el rmiregistry
	LocateRegistry.createRegistry(1099);
	// instancio mi Objeto Remoto y lo publico
	fachada f= new fachada();
	
	System.out.println ("Antes de publicarlo");
	Naming.rebind("//localhost:1099/fachada", f);
	System.out.println ("Luego de publicarlo");
	}
	catch (RemoteException e)
	{ e.printStackTrace(); }
	catch (MalformedURLException e)
	{ e.printStackTrace(); }
	}
}
