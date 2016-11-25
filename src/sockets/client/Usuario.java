package sockets.client;

import javafx.beans.property.SimpleStringProperty;
import model.Pedido;

import java.io.*;
import java.net.Socket;

import static java.lang.Thread.sleep;

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

    public Usuario(String fName, String pClave, String pNumeroCelular, String pDireccion, String pEsAdmin) {
        this.usuario = fName;
        this.clave = pClave;
        this.numeroCelular = pNumeroCelular;
        this.direccion= pDireccion;
        this.admin = esAdmin(pEsAdmin);
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public DataInputStream getEntradaDatos() {
        return entradaDatos;
    }

    public DataOutputStream getSalidaDatos() {
        return salidaDatos;
    }

    public ObjectInputStream getEntradaObjetos() {
        return entradaObjetos;
    }

    public ObjectOutputStream getSalidaObjetos() {
        return salidaObjetos;
    }

    public void setAdmin(boolean _admin){
        this.admin = _admin;
    }

    /**
     * Verifica si el usuario es administrador  cliente
     * @return true -> administrador false -> cliente
     */
    public boolean isAdmin() {
        return admin;
    }

    public String getClave() {
        return clave;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public String getUsuario() {
        return usuario;
    }

    private boolean esAdmin(String esAdmin){

        if(esAdmin.equals("true"))
            return true;
        return false;
    }

    /**
     * hace la peticion para conectrse al servidor
     */
    public void abrirConexion() {
        try {
            this.socket = new Socket("localhost", 6564);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * obtiene la entrad y salida de datos y obtejos
     * del usuario
     */
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

    /**
     * cierra la conexion con el servidor
     */
    public void cerrarConexion(){
        try {
            sleep(500);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
