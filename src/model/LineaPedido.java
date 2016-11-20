package model;

import java.util.ArrayList;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 15-Nov-16.
 */
public class LineaPedido {
    private int precio;
    private int cantidadPiezas;
    private Platillo platillo;

    public LineaPedido(int cantidad, int cantidadPiezas, Platillo platillo) {
        this.precio = cantidad;
        this.cantidadPiezas = cantidadPiezas;
        this.platillo = platillo;
    }

    public int getCantidad() {
        return precio;
    }

    public void setCantidad(int cantidad) {
        this.precio = cantidad;
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
