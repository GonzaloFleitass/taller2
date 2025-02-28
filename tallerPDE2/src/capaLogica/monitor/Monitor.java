package capaLogica.monitor;

public class Monitor {
	private boolean escribiendo;
	private int cantidadLectores;
	private static Monitor instancia;

	public Monitor() {

	}

	public synchronized static Monitor getInstance() {
		if (instancia == null) {
			instancia = new Monitor();
		}

		return instancia;
	}

	public synchronized void comienzoLectura() {
		while (escribiendo) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cantidadLectores++;
	}

	public synchronized void terminoLectura() {
		cantidadLectores--;
		if (cantidadLectores == 0) {
			notify();
		}
	}

	public synchronized void comienzoEscritura() {
		while (cantidadLectores > 0 || escribiendo) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		escribiendo = true;
	}

	public synchronized void terminoEscritura() {
		escribiendo = false;
		notify();
	}
}
