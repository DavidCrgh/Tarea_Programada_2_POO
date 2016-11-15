package model;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 15-Nov-16.
 */
public class Cliente {
    private String nombre;
    private String numeroCelular;
    private String direccion;

    public Cliente(String nombre, String numeroCelular, String direccion) {
        this.nombre = nombre;
        this.numeroCelular = numeroCelular;
        this.direccion = direccion;
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
}
