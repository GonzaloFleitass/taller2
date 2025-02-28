package capaLogica.boletos;
import java.io.Serializable;
public class Boleto implements Serializable {

	private int numBol;
	private String nombre;
	private int edad;
	private int celular;
	private String codigo;
	
	public Boleto(int numBo, String nomb, int ed, int celu, String cod) {
		numBol = numBo;
		nombre = nomb;
		edad= ed;
		celular = celu;
		codigo = cod;
		
	}
	 // Métodos getter para obtener los atributos de Boleto
	public int getNumBoleto() {
		return numBol;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public int getCelular() {
		return celular;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public char tipoBoleto() {
    	return 'C';
    }
	
}
