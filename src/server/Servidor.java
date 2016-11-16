package server;

import java.io.*;
import java.net.*;

/**
 * Created by Bryan on 11/15/2016.
 */
public class Servidor {
    private ServerSocket servidor;
    private int puerto;
    private Socket usuario;
    private ObjectInputStream entradaObjeto;
    private ObjectOutputStream salidaObjeto;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public Servidor(int _puerto){
        this.puerto=_puerto;
        iniciarServidor();
    }

    public void iniciarServidor(){
        try{
            servidor = new ServerSocket(puerto);
            usuario = new Socket();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public void esucharPeticiones() throws IOException{
        usuario = servidor.accept();

        System.out.println("UsuarioAceptado");

        servidor.accept();
    }
}
