package Server;

import java.io.*;
import java.net.*;

/**
 * Created by Bryan on 11/15/2016.
 */
public class Servidor {
    private ServerSocket servidor;
    private int puerto;
    private Socket administrador;
    private Socket cliente;
    private ObjectInputStream entradaObjeto;
    private ObjectOutputStream salidaObjeto;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public Servidor(int _puerto){
        this.puerto=_puerto;
        iniciarsServidor();
    }

    public void iniciarsServidor(){
        try{
            servidor = new ServerSocket(puerto);
            administrador = new Socket();
            cliente = new Socket();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public void esucharPeticiones() throws  IOException{
        administrador = servidor.accept();
        cliente = servidor.accept();
    }
}
