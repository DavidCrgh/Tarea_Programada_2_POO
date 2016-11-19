package sockets.server;

import model.Platillo;
import model.Utilitarias;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.Thread;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Bryan on 11/16/2016.
 */
public class ThreadServidor extends Thread {
    private Servidor servidor;
    private Socket usuarioSocket;

    private DataOutputStream salidaDato;
    private DataInputStream entradaDato;
    private ObjectInputStream entradaObjeto;
    private ObjectOutputStream salidaObjeto;

    public ThreadServidor(Servidor _servidor, Socket _usuarioSocket){
        this.servidor = _servidor;
        this.usuarioSocket = _usuarioSocket;
    }

    public void run(){
        try{
            salidaDato = new DataOutputStream(usuarioSocket.getOutputStream());
            entradaDato = new DataInputStream(usuarioSocket.getInputStream());
            entradaObjeto = new ObjectInputStream(usuarioSocket.getInputStream());
            salidaObjeto = new ObjectOutputStream(usuarioSocket.getOutputStream());
        }catch(Exception e){
            e.printStackTrace();
        }
        int opcion;
        while(true){
            try{
                sleep(1000);
                opcion= entradaDato.readInt();
                switch(opcion){
                    case 1:
                        salidaDato.writeInt(1);
                        salidaObjeto.writeObject(servidor.platillos);
                        break;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
