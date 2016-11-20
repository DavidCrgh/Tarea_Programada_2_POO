package sockets.server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Creado por David Valverde Garro - 2016034774
 * Fecha: 18-Nov-16 Tiempo: 12:22 AM
 */
public class ThreadPeticiones extends Thread {

    Socket clienteActual;
    Servidor servidor;
    ServerSocket socketPeticiones;
    ThreadServidor threadServidorActual;

    public ThreadPeticiones(Servidor _servidor) {

        servidor=_servidor;
        try {
            socketPeticiones = new ServerSocket(6564);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void run(){

        while(true){
            try {
                clienteActual= socketPeticiones.accept();
                threadServidorActual= new ThreadServidor(servidor,clienteActual);
                threadServidorActual.start();
            }catch(Exception e){
                e.printStackTrace();
            }

        }

    }

}
