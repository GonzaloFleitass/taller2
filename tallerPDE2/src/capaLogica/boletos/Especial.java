package capaLogica.boletos;

public class Especial extends Boleto {

    private double descuento;  

    // Constructor de la clase Especial que hereda de Boleto
    public Especial(int numBo, String nomb, int ed, int celu, String cod, double desc) {
        super(numBo, nomb, ed, celu, cod);  // Llama al constructor de la clase base Boleto
        descuento = desc;  // Asigna el descuento a este boleto especial
    }

    // Método para obtener el valor del descuento
    public double getDesc() {
        return descuento;
    }

    // Sobrescribe el método tipoBoleto() para devolver 'E' que indica que es un boleto especial
    @Override
    public char tipoBoleto() {
        return 'E';  // 'E' representa un boleto especial
    }
}
