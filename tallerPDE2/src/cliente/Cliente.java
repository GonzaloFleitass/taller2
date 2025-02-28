package cliente;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import capaLogica.Ifachada;

import java.rmi.NotBoundException;


public class Cliente {
	public static void main (String [] args)
	{ try
	{ Ifachada f = (Ifachada)Naming.lookup("//localhost:1099/fachada");
	
	
	
	
	
	
	
	}
	catch (MalformedURLException e) {e.printStackTrace();}
	catch (RemoteException e) {e.printStackTrace();}
	catch (NotBoundException e) {e.printStackTrace();}
	}
	} 