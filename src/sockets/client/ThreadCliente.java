package sockets.client;

import interfaz.ControladorInicio;
import interfaz.ControladorPrincipalAdministrador;
import interfaz.ControladorPrincipalCliente;
import javafx.application.Platform;
import model.Platillo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Creado por David Valverde Garro - 2016034774
 * Fecha: 17-Nov-16 Tiempo: 3:11 PM
 */
public class ThreadCliente extends Thread {
    private ControladorPrincipalAdministrador controladorAdministrador;
    private ControladorPrincipalCliente controladorCliente;
    private Usuario usuario;

    public ThreadCliente(ControladorPrincipalAdministrador ventana, Usuario _usuario){
        controladorAdministrador = ventana;
        usuario = _usuario;
    }

    public ThreadCliente(ControladorPrincipalCliente ventana, Usuario _usuario){
        controladorCliente = ventana;
        usuario = _usuario;
    }

    public void run(){
        int opcion;

        while(true){
            try{
                sleep(1000);
                opcion = usuario.getEntradaDatos().readInt();
                switch(opcion){
                    case 1:
                        ArrayList<Platillo> platillos = (ArrayList<Platillo>) usuario.getEntradaObjetos().readObject();

                        if(controladorCliente != null){
                            Platform.runLater(() -> {
                                controladorCliente.construirTabla(platillos);
                            });
                        }
                        if(controladorAdministrador != null){
                            Platform.runLater(() -> {
                                controladorAdministrador.platillos=platillos;
                                controladorAdministrador.construirTabla(platillos);

                            });
                        }
                        break;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

