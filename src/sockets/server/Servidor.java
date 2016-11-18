package sockets.server;

import java.io.*;
import java.net.*;

/**
 * Created by Bryan on 11/15/2016.
 */
public class Servidor {
    private ServerSocket servidor;
    private int puerto;
    private Socket cliente1;
    private Socket cliente2;
    private ObjectInputStream entradaObjeto;
    private ObjectOutputStream salidaObjeto;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private ThreadPeticiones hiloPeticiones;

    public Servidor(int _puerto){
        this.puerto=_puerto;
        hiloPeticiones= new ThreadPeticiones(this);
        iniciarServidor();
    }

    public void iniciarServidor(){
    //    try{
            hiloPeticiones.start();
          //  cliente1 = new Socket();
        //    cliente2 = new Socket();
 //       }
    //    catch (Exception e){
   //         System.out.println(e);
       // }

    }

    public void esucharPeticiones() throws IOException{
        cliente1 = servidor.accept();
        new ThreadServidor(this,cliente1).start();
        System.out.println("Cliente Aceptado");

        cliente2 = servidor.accept();
        new ThreadServidor(this,cliente2).start();
        System.out.println("Cliente Aceptado");
    }
}
