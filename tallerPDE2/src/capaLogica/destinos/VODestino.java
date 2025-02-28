package capaLogica.destinos;

public class VODestino {
			private String nombre;
			
			public VODestino(String nomb)
			{ nombre = nomb;}
			
			public String getNombre() {
				return nombre;
			}
			
			public void printVODestino(){
				 System.out.println("Destino: " + nombre); 
			}
		}

