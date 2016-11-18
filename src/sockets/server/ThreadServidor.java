package sockets.server;

import java.lang.Thread;
import java.net.Socket;

/**
 * Created by Bryan on 11/16/2016.
 */
public class ThreadServidor extends Thread {
    private Servidor servidor;
    private Socket usuarioSocket;

    public ThreadServidor(Servidor _servidor, Socket _usuarioSocket){
        this.servidor = _servidor;
        this.usuarioSocket=_usuarioSocket;
    }

    public void run(){
        while(true){
            try{
                sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}

