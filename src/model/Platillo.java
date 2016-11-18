package model;

import java.io.Serializable;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 15-Nov-16.
 */
public class Platillo implements Serializable{
    private String codigo;
    private String nombre;
    private String descripcion;
    private int tamanoPorcion;
    private int caloriasPorcion;
    private int piezasPorcion;
    private int precio;
    private String imagen;
    private boolean disponible;
    private String disponibleString;

    public Platillo(String codigo, String nombre, String descripcion, int tamanoPorcion, int caloriasPorcion, int piezasPorcion, int precio, String imagen, boolean disponible) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tamanoPorcion = tamanoPorcion;
        this.caloriasPorcion = caloriasPorcion;
        this.piezasPorcion = piezasPorcion;
        this.precio = precio;
        this.imagen = imagen;
        this.disponible = disponible;
        this.disponibleString = disponibleString(this.disponible);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTamanoPorcion() {
        return tamanoPorcion;
    }

    public void setTamanoPorcion(int tamanoPorcion) {
        this.tamanoPorcion = tamanoPorcion;
    }

    public int getCaloriasPorcion() {
        return caloriasPorcion;
    }

    public void setCaloriasPorcion(int caloriasPorcion) {
        this.caloriasPorcion = caloriasPorcion;
    }

    public int getPiezasPorcion() {
        return piezasPorcion;
    }

    public void setPiezasPorcion(int piezasPorcion) {
        this.piezasPorcion = piezasPorcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getDisponibleString() {
        return disponibleString;
    }

    public void setDisponibleString(String disponibleString) {
        this.disponibleString = disponibleString;
    }


    private String disponibleString(boolean disponible){

        if(disponible)
            return "Si";
        return "No";
    }
}
