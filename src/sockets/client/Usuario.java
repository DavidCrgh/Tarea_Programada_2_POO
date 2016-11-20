package sockets.client;

import javafx.beans.property.SimpleStringProperty;
import model.Pedido;

import java.io.*;
import java.net.Socket;

/**
 * Creado por David Valverde Garro - 2016034774
 * el 10-Nov-16.
 */
public class Usuario implements Serializable{
    private String usuario;
    private String clave;
    private String numeroCelular;
    private String direccion;
    private boolean admin;

    //Sockets y Objetos de I/O
    private Socket socket;
    private DataInputStream entradaDatos;
    private DataOutputStream salidaDatos;
    private ObjectInputStream entradaObjetos;
    private ObjectOutputStream salidaObjetos;

    public String getDireccion() {
        return direccion;
    }

    public Usuario(String fName, String pClave, String pNumeroCelular, String pDireccion, String pEsAdmin) {
        this.usuario = fName;
        this.clave = pClave;
        this.numeroCelular = pNumeroCelular;
        this.direccion= pDireccion;
        this.admin = esAdmin(pEsAdmin);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataInputStream getEntradaDatos() {
        return entradaDatos;
    }

    public void setEntradaDatos(DataInputStream entradaDatos) {
        this.entradaDatos = entradaDatos;
    }

    public DataOutputStream getSalidaDatos() {
        return salidaDatos;
    }

    public void setSalidaDatos(int s) {
        try {
            this.salidaDatos.writeInt(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectInputStream getEntradaObjetos() {
        return entradaObjetos;
    }

    public void setEntradaObjetos(ObjectInputStream entradaObjetos) {
        this.entradaObjetos = entradaObjetos;
    }

    public ObjectOutputStream getSalidaObjetos() {
        return salidaObjetos;
    }

    public void setSalidaObjetos(Pedido s) {
        try {
            this.salidaObjetos.writeObject(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAdmin(boolean _admin){
        this.admin = _admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNumeroCelualar() {
        return numeroCelular;
    }

    public void setNumeroCelualar(String numeroCelualar) {
        this.numeroCelular = numeroCelualar;
    }

    public String getUsuario() {
        return usuario;
    }

    private boolean esAdmin(String esAdmin){

        if(esAdmin.equals("true"))
            return true;
        return false;
    }

    public void abrirConexion() {
        try {
            this.socket = new Socket("localhost", 6564);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void obtenerFlujos(){
        try {
            salidaDatos = new DataOutputStream(socket.getOutputStream());
            salidaObjetos = new ObjectOutputStream(socket.getOutputStream());
            salidaDatos.flush();
            salidaObjetos.flush();
            entradaDatos = new DataInputStream(socket.getInputStream());
            entradaObjetos = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
