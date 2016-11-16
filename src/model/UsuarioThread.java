package model;

import java.lang.Thread;
import java.net.Socket;
import server.*;

/**
 * Created by Bryan on 11/16/2016.
 */
public class UsuarioThread extends Thread {
    private Socket usuarioSocket;
    private Usuario usuario;

    public UsuarioThread(Socket _usuarioSocket, Usuario _usuario){
        this.usuarioSocket=_usuarioSocket;
        this.usuario=_usuario;
    }

    public void run(){
        if(usuario.isAdmin()) {
            while (true){
                System.out.println("Adminnistrador Corriendo");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            while (true) {
                System.out.println("Cliente corriendo");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

