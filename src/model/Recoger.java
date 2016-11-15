package model;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 15-Nov-16.
 */
public class Recoger extends Pedido {
    private Cliente clienteRecoge;
    private static int precioEmpaque;

    public Recoger(Cliente _cliente, Cliente clienteRecoge) {
        super(_cliente);
        this.clienteRecoge = clienteRecoge;
    }

    public Cliente getClienteRecoge() {
        return clienteRecoge;
    }

    private void setClienteRecoge(Cliente clienteRecoge) {
        this.clienteRecoge = clienteRecoge;
    }

    public static int getPrecioEmpaque() {
        return precioEmpaque;
    }

    public static void setPrecioEmpaque(int precioEmpaque) {
        Recoger.precioEmpaque = precioEmpaque;
    }

    public int calcularPrecioTotal(){
        return 0; //TODO Determinar como se calcula el precio total
    }
}
