package model;

import sockets.client.Usuario;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 15-Nov-16.
 */
public class Express extends Pedido{
    private static int cobroTransporte;

    public Express(Usuario _cliente) {
        super(_cliente);
    }

    public static int getCobroTransporte() {
        return cobroTransporte;
    }

    public static void setCobroTransporte(int cobroTransporte) {
        Express.cobroTransporte = cobroTransporte;
    }

    public int calcularPrecioTotal(){
        return 0; //TODO Determinar como se calcula el precio total
    }
}
