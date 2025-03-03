package capaGrafica;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalTime;
import capaLogica.Ifachada;
import capaLogica.destinos.Destino;
import capaLogica.paseos.paseoException;


public class ControladorIngresoPaseo {

    private Ifachada fach;
    private VentanaIngresoPaseo VIngPas;

    public ControladorIngresoPaseo (VentanaIngresoPaseo VIngPas) {

        this.VIngPas = VIngPas; // Guardar la referencia de la ventana

        try {
            // Hacer el lookup del objeto remoto
            this.fach = (Ifachada) Naming.lookup("//localhost:1099/fachada");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void ingresoPaseo(String cod, Destino dest, LocalTime hpart, LocalTime hllega, Double prec) throws RemoteException, paseoException {
        try {

            fach.insertPaseo(cod, dest, hpart, hllega, prec);


        } catch (paseoException e) {
            System.out.println("Error de paseo: " + e.darMensaje()); // Log del error en consola
            throw e; // Propagar la excepción paseoException
        } catch (RemoteException e) {
            System.out.println("Error de RMI: " + e.getMessage()); // Log del error de RMI
            throw e; // Propagar la excepción RemoteException
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage()); // Log de otros errores inesperados
            e.printStackTrace();
            throw new RemoteException("Error inesperado al ingresar paseo: " + e.getMessage(), e); // Propagar como RemoteException
        }
    }
}