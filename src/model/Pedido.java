package model;

import sockets.client.Usuario;

import javax.sound.sampled.Line;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 15-Nov-16.
 */
public class Pedido implements Serializable{
    private static int pedidosRealizados;
    private Date fecha;
    private Cliente cliente;
    private int numeroPedido;
    private ArrayList<LineaPedido> lineasPedido;
    public tipoPedido tipo;

    public Pedido(Cliente _cliente){
        pedidosRealizados++;
        numeroPedido = pedidosRealizados;
        cliente = _cliente;
        fecha = new Date();
        lineasPedido = new ArrayList<>();
        tipo=tipoPedido.ENSITIO;
    }

    public Pedido(Cliente _cliente, ArrayList<LineaPedido>arrayList){
        pedidosRealizados++;
        numeroPedido = pedidosRealizados;
        cliente = _cliente;
        fecha = new Date();
        lineasPedido = arrayList;
    }

    public static int getPedidosRealizados() {
        return pedidosRealizados;
    }

    public static void setPedidosRealizados(int pedidosRealizados) {
        Pedido.pedidosRealizados = pedidosRealizados;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public ArrayList<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }

    public void agregarLinea(int cantidad, int cantidadPiezas, Platillo platillo){
        lineasPedido.add(new LineaPedido(cantidad,cantidadPiezas,platillo));
    }

    public int calcularPrecioTotal(){
        return 0; //TODO Determinar como se calcula el precio
    }

    public void setTipo(tipoPedido tipo) {
        this.tipo = tipo;
    }
}
