package sockets.client;

import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.net.Socket;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 10-Nov-16.
 */
public class Usuario {
    private SimpleStringProperty usuario;
    private final SimpleStringProperty clave;
    private final SimpleStringProperty numeroCelular;
    private final SimpleStringProperty direccion;
    private boolean admin;
    private Socket cliente;

    public String getDireccion() {
        return direccion.get();
    }

    public SimpleStringProperty direccionProperty() {
        return direccion;
    }

    public Usuario(String fName, String pClave, String pNumeroCelular, String pDireccion, String pEsAdmin) {
        this.usuario = new SimpleStringProperty(fName);
        this.clave = new SimpleStringProperty(pClave);
        this.numeroCelular = new SimpleStringProperty(pNumeroCelular);
        this.direccion= new SimpleStringProperty(pDireccion);
        this.admin = esAdmin(pEsAdmin);
    }

    public void setAdmin(boolean _admin){
        this.admin = _admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getClave() {
        return clave.get();
    }

    public void setClave(String clave) {
        this.clave.set(clave);
    }

    public String getNumeroCelualar() {
        return numeroCelular.get();
    }

    public void setNumeroCelualar(String numeroCelualar) {
        this.numeroCelular.set(numeroCelualar);
    }

    public String getUsuario() {
        return usuario.get();
    }

    public boolean esAdmin(String esAdmin){

        if(esAdmin.equals("true"))
            return true;
        return false;

    }

    public void abrirConexion() {
        try {
            this.cliente = new Socket("localhost", 8080);
        }
        catch (IOException e) {System.out.println(e);}
    }
}
