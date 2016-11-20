package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 15-Nov-16.
 */
public class ListaPedidos implements Serializable{
    private int cantidad;
    private ArrayList<Pedido> pedidos;

    public ListaPedidos() {
        pedidos = new ArrayList<>();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void agregarPedido(Pedido pedido){
        pedidos.add(pedido);
        cantidad++;
        System.out.println("true");
    }

    public ListaPedidos obtenerTop10(){
        return new ListaPedidos();
    }

    public ListaPedidos obtenerNuncaPedidos(){
        return new ListaPedidos();
    }

    public int[] calcularRelacionRES(){
        int arreglo[] = {0,0,0};

        for (Pedido pedido : pedidos) {
            if(pedido.tipo==tipoPedido.ENSITIO)
                arreglo[0]+=1;
            else{
                if(pedido.tipo==tipoPedido.RECOGER)
                    arreglo[1]+=1;
                else
                    arreglo[2]+=1;
            }
        }
        return arreglo;
    }
}
