package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 15-Nov-16.
 * Clase platilloAuxiliar, s eutiliza para construir la tabla de platillos en la interfaz
 */
public class Cliente implements Serializable {
    private String nombre;
    private String numeroCelular;
    private String direccion;
    public String fechaPedido;
    public String precioPedido;
    public Pedido pedidoEnviado;

    public Cliente(String nombre, String numeroCelular, String direccion) {
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
        this.direccion = direccion;
        this.fechaPedido = "";
        this.precioPedido = "0";
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha(Date pFecha) {

        Calendar calendario;
        calendario = Calendar.getInstance();
        pFecha = calendario.getTime();
        SimpleDateFormat mascara = new SimpleDateFormat("dd/MM/yy");
        return mascara.format(pFecha);

    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getPrecioPedido() {
        return precioPedido;
    }

    public void setPrecioPedido(String precioPedido) {
        this.precioPedido = precioPedido;
    }
}
