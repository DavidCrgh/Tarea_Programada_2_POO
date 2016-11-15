package model;

import java.util.ArrayList;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 15-Nov-16.
 */
public class LineaPedido {
    private int cantidad;
    private int cantidadPiezas;
    private Platillo platillo;

    public LineaPedido(int cantidad, int cantidadPiezas, Platillo platillo) {
        this.cantidad = cantidad;
        this.cantidadPiezas = cantidadPiezas;
        this.platillo = platillo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadPiezas() {
        return cantidadPiezas;
    }

    public void setCantidadPiezas(int cantidadPiezas) {
        this.cantidadPiezas = cantidadPiezas;
    }

    public Platillo getPlatillo() {
        return platillo;
    }

    public void setPlatillo(Platillo platillo) {
        this.platillo = platillo;
    }

    public int calcularTotalCalorias(){
        return 0;//TODO Determinar como se calcula el total
    }

    public int calculartotalCosto(){
        return 0; //TODO Determinar como se calcula el total
    }
}
