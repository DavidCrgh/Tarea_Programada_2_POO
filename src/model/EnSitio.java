package model;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 15-Nov-16.
 */
public class EnSitio extends Pedido {
    public EnSitio(Cliente _cliente) {
        super(_cliente);
    }

    public int calcularPrecioTotal(){
        return 0; //TODO Determinar como se calcula el precio total
    }
}